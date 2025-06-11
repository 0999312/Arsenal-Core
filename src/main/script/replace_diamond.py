import os

def replace_diamond_with_copper(root_dir):
    for dirpath, dirnames, filenames in os.walk(root_dir):
        for filename in filenames:
            if filename.endswith(".json"):
                full_path = os.path.join(dirpath, filename)

                # 替换文件内容
                try:
                    with open(full_path, 'r', encoding='utf-8') as file:
                        content = file.read()
                    new_content = content.replace("has_netherite_ingot", "has_blue_and_white_porcelain_piece").replace("Diamond", "Copper").replace("DIAMOND", "COPPER")
                    if new_content != content:
                        with open(full_path, 'w', encoding='utf-8') as file:
                            file.write(new_content)
                        print(f"✅ 内容替换: {full_path}")
                except Exception as e:
                    print(f"❌ 读取/写入失败: {full_path}，错误: {e}")

                # 替换文件名
                new_filename = filename
                if "netherite" in filename.lower():
                    new_filename = new_filename.replace("netherite", "blue_and_white_porcelain")
                if "diamond" in filename.lower():
                    new_filename = new_filename.replace("Diamond", "Copper").replace("DIAMOND", "COPPER").replace("diamond", "Copper")
                
                if new_filename != filename:
                    new_path = os.path.join(dirpath, new_filename)
                    try:
                        os.rename(full_path, new_path)
                        print(f"🔁 重命名文件: {filename} -> {new_filename}")
                    except Exception as e:
                        print(f"❌ 文件重命名失败: {full_path} -> {new_path}，错误: {e}")

# 使用方式：将下面路径换成你的目标文件夹路径
replace_diamond_with_copper(r"F:\code\mcmod\project\arsenal-core-reborn\src\main\resources\assets\arsenal\textures\item")