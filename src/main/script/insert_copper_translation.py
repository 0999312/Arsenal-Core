import os
import re

# 替换逻辑
KEY_REPLACEMENTS = [
    ("stone_", "blue_and_white_porcelain_"),  # item key
    ("tier.arsenal.stone", "tier.arsenal.blue_and_white_porcelain"),  # tier key
]

VALUE_REPLACEMENTS = {
    "石質": "青花",
    "石质": "青花",
    "石": "青花瓷",
    "stone": "blue_and_white_porcelain",
    "Stone": "Blue and White Porcelain",
}

def smart_replace(text):
    for k, v in VALUE_REPLACEMENTS.items():
        if k in text:
            return text.replace(k, v)
    return text

def process_json_lines(filepath):
    with open(filepath, 'r', encoding='utf-8') as f:
        lines = f.readlines()

    new_lines = []
    inserted_keys = set()

    for i, line in enumerate(lines):
        stripped = line.strip()
        new_lines.append(line)

        # 匹配 "key": "value" 格式
        match = re.match(r'"([^"]+)"\s*:\s*"([^"]+)"\s*,?', stripped)
        if not match:
            continue

        key, value = match.groups()
        for key_from, key_to in KEY_REPLACEMENTS:
            if key_from in key:
                new_key = key.replace(key_from, key_to)
                if new_key in inserted_keys or any(f'"{new_key}"' in l for l in lines):
                    continue  # 已存在或已插入

                new_value = smart_replace(value)
                indent = " " * (len(line) - len(line.lstrip()))

                # 构建并准备插入行，暂时不决定是否加逗号
                insert_line = f'{indent}"{new_key}": "{new_value}"'

                # 插入前检查是否需要加逗号
                if i + 1 < len(lines) and ('}' not in lines[i + 1].strip()):
                    insert_line += ','

                insert_line += '\n'
                new_lines.append(insert_line)
                inserted_keys.add(new_key)

    # 覆盖写回
    with open(filepath, 'w', encoding='utf-8') as f:
        f.writelines(new_lines)

    print(f"✅ 插入完成：{filepath}")

def process_folder(folder):
    for root, _, files in os.walk(folder):
        for file in files:
            if file.endswith(".json"):
                process_json_lines(os.path.join(root, file))

# ✅ 替换为你的目录路径
process_folder(r"F:\code\mcmod\project\arsenal-core-reborn\src\main\resources\assets\arsenal_core\lang")