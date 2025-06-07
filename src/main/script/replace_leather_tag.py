import os
import json

def replace_leather_tag(path):
    for root, dirs, files in os.walk(path):
        for filename in files:
            if filename.endswith(".json"):
                file_path = os.path.join(root, filename)
                try:
                    with open(file_path, "r", encoding="utf-8") as f:
                        data = json.load(f)

                    if isinstance(data, dict) and "key" in data:
                        changed = False
                        for k, v in data["key"].items():
                            if isinstance(v, dict) and v.get("tag") == "c:cobblestone":
                                v["tag"] = "c:cobblestones"
                                changed = True

                        if changed:
                            print(f"Patched: {file_path}")
                            with open(file_path, "w", encoding="utf-8") as f:
                                json.dump(data, f, ensure_ascii=False, indent=4)
                except Exception as e:
                    print(f"Failed to process {file_path}: {e}")

target_directory = r"F:\code\mcmod\project\arsenal-core-reborn\src\main\resources\data\arsenal_core\recipes"

replace_leather_tag(target_directory)
