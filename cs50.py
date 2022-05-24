
"""
# pythonic do-while loop
while True:
    n = int(input("Height: "))
    if n > 0:
        break


for i in range(n):
    # end 在文本末尾起作用, end="", 相当于覆盖了原本的 "\n"
    print("#", end = "")
print()
print("<" * 4) # pirnt 4 times 
"""

scores = []  # 变长列表
for i in range(3):
    scores.append(int(input("Score: "))
    # scores = scores + [score]  另一种方法相连  列表相加
)

average = sum(scores) / len(scores)
print(f"Average: {average}")

# dictionary  key: value
people = {
    "David": "88979",
    "Carrie": "789"
}
name = input("Name: ")
if name in people:
    number = people[name]
    print(f"Number: {number}")