import os
import re
from collections import defaultdict

KEY_REPLACEMENTS = [
    ("stone_", "copper_"),
    ("tier.arsenal.stone", "tier.arsenal.copper"),
]

VALUE_REPLACEMENTS = {
    "石質": "銅質",
    "石质": "铜质",
    "石": "铜",
    "stone": "copper",
    "Stone": "Copper",
    "STONE": "COPPER"
}

def smart_replace(text):
    for k, v in VALUE_REPLACEMENTS.items():
        text = text.replace(k, v)
    return text

def process_json_lines(filepath):
    with open(filepath, 'r', encoding='utf-8') as f:
        lines = f.readlines()

    new_lines = []
    inserted_keys = set()
    copper_buffer = defaultdict(list)
    last_stone_group_end = {}  # key: group id -> line index

    for i, line in enumerate(lines):
        new_lines.append(line)
        stripped = line.strip()
        match = re.match(r'"([^"]+)"\s*:\s*"([^"]+)"\s*,?', stripped)
        if not match:
            continue

        key, value = match.groups()
        for key_from, key_to in KEY_REPLACEMENTS:
            if key_from in key:
                new_key = key.replace(key_from, key_to)
                if new_key in inserted_keys or any(f'"{new_key}"' in l for l in lines):
                    continue

                new_value = smart_replace(value)
                indent = " " * (len(line) - len(line.lstrip()))
                insert_line = f'{indent}"{new_key}": "{new_value}",\n'

                # 将新行缓存起来，稍后再插入
                copper_buffer[key_from].append((i, insert_line))
                inserted_keys.add(new_key)

                # 记录本组的最后一行位置（不断更新，最终为该组的最后一行）
                last_stone_group_end[key_from] = i

    # 反向插入所有 copper 行（从后往前插入避免行号错乱）
    for key_from, inserts in copper_buffer.items():
        end_index = last_stone_group_end.get(key_from)
        if end_index is not None:
            insertion = [line for _, line in inserts]
            new_lines[end_index + 1:end_index + 1] = insertion

    # 移除最后一项的多余逗号（如果有）
    for j in range(len(new_lines) - 1, -1, -1):
        if new_lines[j].strip().endswith(",") and j + 1 < len(new_lines) and new_lines[j + 1].strip().startswith("}"):
            new_lines[j] = new_lines[j].rstrip().rstrip(",") + "\n"
            break

    with open(filepath, 'w', encoding='utf-8') as f:
        f.writelines(new_lines)

    print(f"✅ 插入完成：{filepath}")

def process_folder(folder):
    for root, _, files in os.walk(folder):
        for file in files:
            if file.endswith(".json"):
                process_json_lines(os.path.join(root, file))

# ✅ 替换为你的目录路径
process_folder(r"F:\code\mcmod\project\arsenal-core-reborn\src\main\resources\assets\arsenal\textures\item")
