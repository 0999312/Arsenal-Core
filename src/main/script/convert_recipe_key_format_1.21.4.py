import os
import json

# 你的配方目录
recipe_dir = r"F:\code\mcmod\project\arsenal-core-reborn\src\main\resources\data\arsenal_core\recipe"

for filename in os.listdir(recipe_dir):
    if not filename.endswith(".json"):
        continue

    full_path = os.path.join(recipe_dir, filename)

    with open(full_path, "r", encoding="utf-8") as f:
        try:
            data = json.load(f)
        except json.JSONDecodeError:
            print(f"跳过无效 JSON：{filename}")
            continue

    # 只处理 crafting_shaped 配方
    if data.get("type") != "minecraft:crafting_shaped":
        continue

    key = data.get("key")
    if not isinstance(key, dict):
        continue

    changed = False
    new_key = {}

    for k, v in key.items():
        # 如果是标准对象
        if isinstance(v, dict):
            if "item" in v:
                new_key[k] = v["item"]
                changed = True
            elif "tag" in v:
                new_key[k] = f"#{v['tag']}"
                changed = True
            else:
                new_key[k] = v
        else:
            new_key[k] = v  # 保留原样（已是字符串）

    if changed:
        data["key"] = new_key
        with open(full_path, "w", encoding="utf-8") as f:
            json.dump(data, f, indent=4)
        print(f"已转换：{filename}")
