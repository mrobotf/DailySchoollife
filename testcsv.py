import csv

file = open("phonebook.csv", "a")  # 追加模式

name = input("Name: ")
number = input("Number: ")

# 向 csv 库中请求该文件的写入器
writer = csv.writer(file)
writer.writerow([name, number])

file.close()


"""***********************"""
# 使用 with 关键字，不用最后使用关闭操作
name = input("Name: ")
number = input("Number: ")
with open("phonebook.csv", "a") as file:

    # 向 csv 库中请求该文件的写入器
    writer = csv.writer(file)
    writer.writerow([name, number])