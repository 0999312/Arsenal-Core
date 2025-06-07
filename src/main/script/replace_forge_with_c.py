import os

def replace_forge_with_neoforge(directory):
    for root, _, files in os.walk(directory):
        for filename in files:
            if filename.endswith(".json"):
                file_path = os.path.join(root, filename)
                try:
                    with open(file_path, "r", encoding="utf-8") as f:
                        content = f.read()
                    new_content = content.replace("forge:", "c:")
                    if new_content != content:
                        with open(file_path, "w", encoding="utf-8") as f:
                            f.write(new_content)
                        print(f"✔ 已替换：{file_path}")
                except Exception as e:
                    print(f"⚠ 错误处理文件 {file_path}：{e}")

target_folder = r"F:\code\mcmod\project\arsenal-core-reborn\src\main\resources\data\arsenal_core\recipes"
replace_forge_with_neoforge(target_folder)
