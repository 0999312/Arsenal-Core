import os
import json

# 输入目录：模型文件原始路径
source_dir = r"F:\code\mcmod\project\arsenal-core-reborn\src\main\resources\assets\arsenal_core\models\item"
# 输出目录：新的 items 模型路径
output_dir = r"/assets/arsenal_core/item"

# 创建输出目录（如果不存在）
os.makedirs(output_dir, exist_ok=True)

# 遍历所有 json 文件
for file_name in os.listdir(source_dir):
    if not file_name.endswith(".json"):
        continue

    base_name = file_name[:-5]  # 去除 .json
    if base_name == "weapon_frog":
        continue  # 跳过特殊文件

    output_path = os.path.join(output_dir, f"{base_name}.json")

    if base_name.endswith("_sheath") or base_name.endswith("_scabbard"):
        # sheath / scabbard 类型
        content = {
            "model": {
                "type": "minecraft:model",
                "model": f"arsenal_core:item/{base_name}"
            }
        }

    elif base_name.endswith("_blocking"):
        continue  # 跳过 _blocking 文件本身

    else:
        # 主武器模型：生成 condition 类型
        content = {
            "model": {
                "type": "minecraft:condition",
                "property": "minecraft:using_item",
                "on_true": {
                    "type": "minecraft:model",
                    "model": f"arsenal_core:item/{base_name}_blocking"
                },
                "on_false": {
                    "type": "minecraft:model",
                    "model": f"arsenal_core:item/{base_name}"
                }
            }
        }

    # 写入生成文件
    with open(output_path, "w", encoding="utf-8") as f:
        json.dump(content, f, indent=2)
        print(f"生成：{output_path}")
