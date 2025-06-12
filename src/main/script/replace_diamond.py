import os

def replace_diamond_with_copper(root_dir):
    for dirpath, dirnames, filenames in os.walk(root_dir):
        for filename in filenames:
            if filename.endswith(".json"):
                full_path = os.path.join(dirpath, filename)

                # 替换文件内容
                with open(full_path, 'r', encoding='utf-8') as file:
                    content = file.read()
                new_content = content.replace("diamond", "copper").replace("Diamond", "Copper").replace("DIAMOND", "COPPER")
                if new_content != content:
                    with open(full_path, 'w', encoding='utf-8') as file:
                        file.write(new_content)
                    print(f"✅ 内容替换: {full_path}")

                # 替换文件名
                if "diamond" in filename.lower():
                    new_filename = filename.replace("diamond", "copper").replace("Diamond", "Copper").replace("DIAMOND", "COPPER")
                    new_path = os.path.join(dirpath, new_filename)
                    os.rename(full_path, new_path)
                    print(f"🔁 重命名文件: {filename} -> {new_filename}")

# 使用方式：将下面路径换成你的目标文件夹路径
replace_diamond_with_copper(r"F:\code\mcmod\project\arsenal-core-reborn\src\main\resources\assets\arsenal\textures\item")
