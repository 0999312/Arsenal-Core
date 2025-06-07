import os
import json

def replace_result_item_with_id(path):
    for root, dirs, files in os.walk(path):
        for filename in files:
            if filename.endswith(".json"):
                file_path = os.path.join(root, filename)
                try:
                    with open(file_path, "r", encoding="utf-8") as f:
                        data = json.load(f)

                    # 检查是否含有 result -> item
                    if isinstance(data, dict) and "result" in data:
                        result = data["result"]
                        if isinstance(result, dict) and "item" in result:
                            item_value = result["item"]
                            # 替换为 id 格式
                            data["result"] = {"id": item_value}
                            print(f"Patched: {file_path}")

                            # 写回文件
                            with open(file_path, "w", encoding="utf-8") as f:
                                json.dump(data, f, ensure_ascii=False, indent=4)
                except Exception as e:
                    print(f"Failed to process {file_path}: {e}")

target_directory = r"F:\code\mcmod\project\arsenal-core-reborn\src\main\resources\data\arsenal_core\recipes"

replace_result_item_with_id(target_directory)
