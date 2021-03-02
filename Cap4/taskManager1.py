#Slef study let me to this site to on how to get date and time, with all the different manners of stating the date.**https://www.programiz.com/python-programming/datetime/current-datetime#:~:text=today()%20method%20to%20get,representing%20date%20in%20different%20formats.
from datetime import date

#Setting "Universal date for below.
#And setting the format of the time as it is writting in from user input. (dd-mm-yy)
dates = date.today()
d = dates.strftime("%d-%b-%y")


#As alwyas, greeting user.
print("Good day user.")
print()

#engaing and directing user, using print statement to engaing with user.
print("Please enter your credentials below:")
print()

#For all menus wherethe user needs to select an input I used """ to dsiplay the menu in a 
#creating a menu for users,
#assinging the menu str to variable
menu = ("Please select one of the following options. \n"
        "a - Add Task \n"
        "va - View all Tasks \n"
        "vm - View my Tasks \n"
        "e - Exit \n")

#creating admin only menu with added function,
#assinging the admin only menu str to variable
admin_menu = ("Good day Admin, Please select one of the following options. \n"
        "r - Register User \n"
        "a - Add Task \n"
        "va - View all Tasks \n"
        "vm - View my Tasks \n"
        "tu - View numer of Tasks and Users \n"
        "gr - generate reports \n"
        "ds - display statistics \n"
        "e - Exit \n")

#Defining new function here. Once again I stuck to have individual functions to avoid "individual bloated funcitons".
#I have also left in control points to test print to confirm you get what you expected. **Amendemnt, For resubmission I have have removed all commented out codes inluding control points as suggested.
#This function is to regester new users 
def reg_user():
    print("Please enter new Username & Password below.")
    new_userN = str(input("Username:"))
    new_passW = str(input("Password:"))
    new_passW_check = input("Please confirm Password:")
    print()
    with open("user1.txt", "r") as user_check:
        user_check = user_check.read()
    if new_userN in user_check:
            print("User already exsists. Please enter new Username.")
    elif new_passW == new_passW_check:
        print("Password Confirmed")
        with open("user1.txt", "a+") as user_pass:
            user_pass.write("\n{}, {}.".format(new_userN, new_passW))
    else:
        print("Passwords does not match, please enter credentials again.")
        
#This function is to add tasks and assign them to users
def add_task():
    task_list = open("tasks1.txt", "a+")
    print("Please enter the Task details below.")
    task_user = input("This Task is assigned to: ")
    task = input("The Task: ")
    task_descpt = input("Task Descrpition: ")
    task_due_date = input("Due Date: ")
    task_ass_date = input("Date Assigned: ")
    task_complt = "No"
    task_list.write("\n{}, {}, {}, {}, {}, {}".format(task_user,task,task_descpt,task_due_date,task_ass_date,task_complt))
    task_list.close()

#This function displays all function on created
def view_all():
    with open("tasks1.txt", "r+") as view_tasks:
        view_tasks = view_tasks.readlines()
        for count, view_tasks in enumerate(view_tasks, 0):
            print(count, view_tasks)

#Forthese menus where the user needs to select an input I used """ to dsiplay the menu in the most readable manner.
#This function displays induvidual users tasks and allows to be edited
def view_mine():
     with open("tasks1.txt", "r+") as my_tasks:
            my_tasks = my_tasks.readlines()
            for i, line in enumerate(my_tasks, 0):
                task_user, task, task_descpt, task_due_date, task_ass_date, task_complt = line.split(",")
                if user_name == task_user:
                    print(f"""
Task Nr: {i}
 Assigned User: {task_user}
 Task: {task}
 Task Description: {task_descpt}
 Due by: {task_due_date}
 Assigned on: {task_ass_date}
 Completed: {task_complt}
""")
                    
            user_num = int(input("To edit or mark your task as complete, enter the task number or enter -1 to end. Nr: "))
            for a, line in enumerate(my_tasks, 0):
                task_user, task, task_descpt, task_due_date, task_ass_date, task_complt = line.split(",")
                if user_num == a:
                    print(line) 
                    line = line.split(", ")  
                    print(""""
1 Edit User
2 Edit Task Name
3 Edit Task Description
4 Edit Due Date
5 Edit Assigned date
6 Completion Status""")

                    exchange = input("Please enter by number the parameter to edit:")
                    if exchange == "1":
                        task_user = input("This Task is assigned to: ")
                        line[0] = task_user
                    elif exchange == "2":
                        task = input("The Task: ")
                        line[1] = task
                    elif exchange == "3":
                        task_descpt = input("Task Descrpition: ")
                        line[2] = task_descpt
                    elif exchange == "4":
                        task_due_date = input("Due Date: ")
                        line[3] = task_due_date
                    elif exchange == "5":
                        task_ass_date = input("Date Assigned: ")
                        line[4] = task_ass_date
                    elif exchange == "6":
                        task_complt = input("Completed Yes/No: ")
                        task_complt = "{}\n".format(task_complt)
                        line[5] = task_complt
                    else:
                        pass
                    line = ", ".join(line)
                    pos = int(input("Confirm task number: "))
                    for a, b in enumerate(my_tasks, 0):
                              if pos == a:
                                  my_tasks[a] = line
                                  print(my_tasks)
                    task_list = open("tasks1.txt", "w")
                    task_list.write("".join(my_tasks))
                    task_list.close()             
                else:                    
                    if user_num == -1:   
                        pass
                    
#The list of functions below is all induvidually created to excicute specific functions.
def gen_report1():
    tasks = open("tasks1.txt", "r")
    lines = tasks.readlines()
    lines = len(lines) 
    task_overview = "task_overview.txt"
    writes = open("task_overview.txt", "w")
    writes.write("The total number of tasks generated is: {}\n".format(lines))
    writes.close()
    tasks.close()
def gen_report2():
    tasks = open("tasks1.txt", "r+")
    tasks = tasks.read()
    tasks = tasks.split()
    yes = 0
    for word in tasks:
        if word == "Yes":
            yes += 1
    task_overview = "task_overview.txt"
    writes = open("task_overview.txt", "a+")
    writes.write("The number of completed tasks: {}\n".format(yes))
    writes.close()
def gen_report3():
    tasks = open("tasks1.txt", "r+")
    tasks = tasks.read()
    tasks = tasks.split()
    no = 0
    for word in tasks:
        if word == "No":
            no += 1
    task_overview = "task_overview.txt"
    writes = open("task_overview.txt", "a+")
    writes.write("The number of uncompleted tasks: {}\n".format(no))
    writes.close()
def gen_report4():
    tasks = open("tasks1.txt", "r")
    lines = tasks.readlines()
    lines = len(lines)
    tasks = open("tasks1.txt", "r")
    tasks = tasks.read()
    tasks = tasks.split()
    No = 0
    for word in tasks:
        if word == "No":
            No += 1
    uncom_perc = round(No*100/lines,2)
    task_overview = "task_overview.txt"
    writes = open("task_overview.txt", "a+")
    writes.write("The percentage of incompleted tasks: {}%\n".format(uncom_perc))
    writes.close()
def gen_report56():
    with open("tasks1.txt", "r+") as my_tasks:
        my_tasks = my_tasks.readlines()
        overdue = 0
        for line in my_tasks:
            task_user, task, task_descpt, task_due_date, task_ass_date, task_complt = line.split(", ")
            if task_due_date < d and task_complt == "No\n":
                overdue += 1
    total_overdue = overdue
    t_my_tasks = len(my_tasks)
    u_o_percent = round(total_overdue*100/t_my_tasks,2)
    task_overview = "task_overview.txt"
    writes = open("task_overview.txt", "a+")
    writes.write("There are {} overdue and incomplete tasks on the system.\n".format(total_overdue))
    writes.write("The percentage of tasks over due is {}%.\n".format(u_o_percent))
    writes.close()
def gen_report7():
    user1 = open("user1.txt", "r+")
    num_user = user1.readlines()
    num_user = len(num_user) 
    user_overview = "user_overview.txt"
    writes = open("user_overview.txt", "w")
    writes.write("The total number of users registered is: {}\n".format(num_user))
    writes.close()
    user1.close()
    tasks = open("tasks1.txt", "r+")
    lines = tasks.readlines()
    lines = len(lines)
    writes = open("user_overview.txt", "a+")
    writes.write("The total number of tasks generated is: {}\n".format(lines))
    writes.close()
    tasks.close()
def gen_report81():
    with open("tasks1.txt", "r+") as my_tasks:
            my_tasks = my_tasks.readlines()
            dates = date.today()
            d = dates.strftime("%d-%b-%y")
            task_total = len(my_tasks)
            admin1 = 0
            admin2 = 0
            admin3 = 0
            admin4 = 0
            for line in my_tasks:
                task_user, task, task_descpt, task_due_date, task_ass_date, task_complt = line.split(",")
                if task_user == "admin":
                   admin1 += 1
                if task_user == "admin" and task_complt == " Yes\n":
                   admin2 += 1
                if task_user == "admin" and task_complt == " No\n":
                   admin3 += 1
                if task_user == "admin" and d > task_due_date and task_complt == " No\n":
                   admin4 += 1
    adminx = admin1*100/task_total
    admin_at = round(adminx,2)
    admin_ct = round(admin2*100/admin1,2)
    admin_in = round(admin3*100/admin1,2)
    admin_oi = round(admin4*100/admin1,2)
    writes = open("user_overview.txt", "a+")
    writes.write(f"""
admin Overview:
admin has {admin1} tasks on the system.
There is {admin_at}% of all tasks assigned to admin.
{admin_ct}% of the tasks assigned to admin is completed.
{admin_in}% of the tasks assigned to admin is incomplete.
{admin_oi}% of the tasks assigned to admin is overdue and incomplete.""")
    writes.close()
def gen_report82():
    with open("tasks1.txt", "r+") as my_tasks:
            my_tasks = my_tasks.readlines()
            dates = date.today()
            d = dates.strftime("%d-%b-%y")
            task_total = len(my_tasks)
            admin1 = 0
            admin2 = 0
            admin3 = 0
            admin4 = 0
            for line in my_tasks:
                task_user, task, task_descpt, task_due_date, task_ass_date, task_complt = line.split(",")
                if task_user == "Andro":
                   admin1 += 1
                if task_user == "Andro" and task_complt == " Yes\n":
                   admin2 += 1
                if task_user == "Andro" and task_complt == " No\n":
                   admin3 += 1
                if task_user == "Andro" and d > task_due_date and task_complt == " No\n":
                   admin4 += 1
    adminx = admin1*100/task_total
    admin_at = round(adminx,2)
    admin_ct = round(admin2*100/admin1,2)
    admin_in = round(admin3*100/admin1,2)
    admin_oi = round(admin4*100/admin1,2)
    writes = open("user_overview.txt", "a+")
    writes.write(f"""
Andro Overview:
Andro has {admin1} tasks on the system.
There is {admin_at}% of all tasks assigned to Andro.
{admin_ct}% of the tasks assigned to Andro is completed.
{admin_in}% of the tasks assigned to Andro is incomplete.
{admin_oi}% of the tasks assigned to Andro is overdue and incomplete.""")
    writes.close()
def gen_report83():
    with open("tasks1.txt", "r+") as my_tasks:
            my_tasks = my_tasks.readlines()
            dates = date.today()
            d = dates.strftime("%d-%b-%y")
            task_total = len(my_tasks)
            admin1 = 0
            admin2 = 0
            admin3 = 0
            admin4 = 0
            for line in my_tasks:
                task_user, task, task_descpt, task_due_date, task_ass_date, task_complt = line.split(",")
                if task_user == "Wihan":
                   admin1 += 1
                if task_user == "Wihan" and task_complt == " Yes\n":
                   admin2 += 1
                if task_user == "Wihan" and task_complt == " No\n":
                   admin3 += 1
                if task_user == "Wihan" and d > task_due_date and task_complt == " No\n":
                   admin4 += 1
    adminx = admin1*100/task_total
    admin_at = round(adminx,2)
    admin_ct = round(admin2*100/admin1,2)
    admin_in = round(admin3*100/admin1,2)
    admin_oi = round(admin4*100/admin1,2)
    writes = open("user_overview.txt", "a+")
    writes.write(f"""
Wihan Overview:
Wihan has {admin1} tasks on the system.
There is {admin_at}% of all tasks assigned to Wihan.
{admin_ct}% of the tasks assigned to Wihan is completed.
{admin_in}% of the tasks assigned to Wihan is incomplete.
{admin_oi}% of the tasks assigned to Wihan is overdue and incomplete.""")
    writes.close()
def gen_report84():
    with open("tasks1.txt", "r+") as my_tasks:
            my_tasks = my_tasks.readlines()
            dates = date.today()
            d = dates.strftime("%d-%b-%y")
            task_total = len(my_tasks)
            admin1 = 0
            admin2 = 0
            admin3 = 0
            admin4 = 0
            for line in my_tasks:
                task_user, task, task_descpt, task_due_date, task_ass_date, task_complt = line.split(",")
                if task_user == "Tony":
                   admin1 += 1
                if task_user == "Tony" and task_complt == " Yes\n":
                   admin2 += 1
                if task_user == "Tony" and task_complt == " No\n":
                   admin3 += 1
                if task_user == "Tony" and d > task_due_date and task_complt == " No\n":
                   admin4 += 1
    adminx = admin1*100/task_total
    admin_at = round(adminx,2)
    admin_ct = round(admin2*100/admin1,2)
    admin_in = round(admin3*100/admin1,2)
    admin_oi = round(admin4*100/admin1,2)
    writes = open("user_overview.txt", "a+")
    writes.write(f"""
Tony Overview:
Tony has {admin1} tasks on the system.
There is {admin_at}% of all tasks assigned to Tony.
{admin_ct}% of the tasks assigned to Tony is completed.
{admin_in}% of the tasks assigned to Tony is incomplete.
{admin_oi}% of the tasks assigned to Tony is overdue and incomplete.""")
    writes.close()
def gen_report85():
    with open("tasks1.txt", "r+") as my_tasks:
            my_tasks = my_tasks.readlines()
            dates = date.today()
            d = dates.strftime("%d-%b-%y")
            task_total = len(my_tasks)
            admin1 = 0
            admin2 = 0
            admin3 = 0
            admin4 = 0
            for line in my_tasks:
                task_user, task, task_descpt, task_due_date, task_ass_date, task_complt = line.split(",")
                if task_user == "Thor":
                   admin1 += 1
                if task_user == "Thor" and task_complt == " Yes\n":
                   admin2 += 1
                if task_user == "Thor" and task_complt == " No\n":
                   admin3 += 1
                if task_user == "Thor" and d > task_due_date and task_complt == " No\n":
                   admin4 += 1
    adminx = admin1*100/task_total
    admin_at = round(adminx,2)
    admin_ct = round(admin2*100/admin1,2)
    admin_in = round(admin3*100/admin1,2)
    admin_oi = round(admin4*100/admin1,2)
    writes = open("user_overview.txt", "a+")
    writes.write(f"""
Thor Overview:
Thor has {admin1} tasks on the system.
There is {admin_at}% of all tasks assigned to Thor.
{admin_ct}% of the tasks assigned to Thor is completed.
{admin_in}% of the tasks assigned to Thor is incomplete.
{admin_oi}% of the tasks assigned to Thor is overdue and incomplete.""")
    writes.close()
def gen_report86():
    with open("tasks1.txt", "r+") as my_tasks:
            my_tasks = my_tasks.readlines()
            dates = date.today()
            d = dates.strftime("%d-%b-%y")
            task_total = len(my_tasks)
            admin1 = 0
            admin2 = 0
            admin3 = 0
            admin4 = 0
            for line in my_tasks:
                task_user, task, task_descpt, task_due_date, task_ass_date, task_complt = line.split(",")
                if task_user == "Peter":
                   admin1 += 1
                if task_user == "Peter" and task_complt == " Yes\n":
                   admin2 += 1
                if task_user == "Peter" and task_complt == " No\n":
                   admin3 += 1
                if task_user == "Peter" and d > task_due_date and task_complt == " No\n":
                   admin4 += 1
    adminx = admin1*100/task_total
    admin_at = round(adminx,2)
    admin_ct = round(admin2*100/admin1,2)
    admin_in = round(admin3*100/admin1,2)
    admin_oi = round(admin4*100/admin1,2)
    writes = open("user_overview.txt", "a+")
    writes.write(f"""
Peter Overview:
Peter has {admin1} tasks on the system.
There is {admin_at}% of all tasks assigned to Peter.
{admin_ct}% of the tasks assigned to Peter is completed.
{admin_in}% of the tasks assigned to Peter is incomplete.
{admin_oi}% of the tasks assigned to Peter is overdue and incomplete.""")
    writes.close()
def gen_report87():
    with open("tasks1.txt", "r+") as my_tasks:
            my_tasks = my_tasks.readlines()
            dates = date.today()
            d = dates.strftime("%d-%b-%y")
            task_total = len(my_tasks)
            admin1 = 0
            admin2 = 0
            admin3 = 0
            admin4 = 0
            for line in my_tasks:
                task_user, task, task_descpt, task_due_date, task_ass_date, task_complt = line.split(",")
                if task_user == "Nick":
                   admin1 += 1
                if task_user == "Nick" and task_complt == " Yes\n":
                   admin2 += 1
                if task_user == "Nick" and task_complt == " No\n":
                   admin3 += 1
                if task_user == "Nick" and d > task_due_date and task_complt == " No\n":
                   admin4 += 1
    adminx = admin1*100/task_total
    admin_at = round(adminx,2)
    admin_ct = round(admin2*100/admin1,2)
    admin_in = round(admin3*100/admin1,2)
    admin_oi = round(admin4*100/admin1,2)
    writes = open("user_overview.txt", "a+")
    writes.write(f"""
Nick Overview:
Nick has {admin1} tasks on the system.
There is {admin_at}% of all tasks assigned to Nick.
{admin_ct}% of the tasks assigned to Nick is completed.
{admin_in}% of the tasks assigned to Nick is incomplete.
{admin_oi}% of the tasks assigned to Nick is overdue and incomplete.""")
    writes.close()
def gen_report88():
    with open("tasks1.txt", "r+") as my_tasks:
            my_tasks = my_tasks.readlines()
            dates = date.today()
            d = dates.strftime("%d-%b-%y")
            task_total = len(my_tasks)
            admin1 = 0
            admin2 = 0
            admin3 = 0
            admin4 = 0
            for line in my_tasks:
                task_user, task, task_descpt, task_due_date, task_ass_date, task_complt = line.split(",")
                if task_user == "Pepper":
                   admin1 += 1
                if task_user == "Pepper" and task_complt == " Yes\n":
                   admin2 += 1
                if task_user == "Pepper" and task_complt == " No\n":
                   admin3 += 1
                if task_user == "Pepper" and d > task_due_date and task_complt == " No\n":
                   admin4 += 1
    adminx = admin1*100/task_total
    admin_at = round(adminx,2)
    admin_ct = round(admin2*100/admin1,2)
    admin_in = round(admin3*100/admin1,2)
    admin_oi = round(admin4*100/admin1,2)
    writes = open("user_overview.txt", "a+")
    writes.write(f"""
Pepper Overview:
Pepper has {admin1} tasks on the system.
There is {admin_at}% of all tasks assigned to Pepper.
{admin_ct}% of the tasks assigned to Pepper is completed.
{admin_in}% of the tasks assigned to Pepper is incomplete.
{admin_oi}% of the tasks assigned to Pepper is overdue and incomplete.""")
    writes.close()
    
#This is the most efficient way I found to "run" the DS, Unfortunately I did for get to copy my reference. I do understand 95% of the process.
#Also prefered to use enumerate to display it in a readable manner.
def disp_stats():
    with open("task_overview.txt", "r+") as task_ov:
        task_ov = task_ov.readlines()
        print("\u0332".join("The Tasks stats are:"))
        for count, task_ov in enumerate(task_ov, 1):
            print(count, task_ov)
    with open("user_overview.txt", "r+") as user_ov:
        user_ov = user_ov.readlines()
        print("\u0332".join("The User stast are:"))
        for count, user_ov in enumerate(user_ov, 1):
            print(count, user_ov)
              
#opening user list to read for login details
with open("user1.txt", "r") as user_in:
    user_in = user_in.read()


#first while loop to create login and creating "admin" log in as per second part of capstone.
while True:
    user_name = input("Username: ")
    user_pass = input("Password: ")
    print()
    if user_name == "admin" and user_pass == "adm1n":
        print(admin_menu)
        break
    if user_name in user_in and user_pass in user_in:
        print(menu)
        break
    else:
        print("Credentials not found. ")
        print()
        continue

#Second while loop to run option menu.
while True:
    print()
    option_selct = input("Menu Selection:")
    print()

#first if statement to run new user register and wright new users and passwords to user.txt.
    if option_selct == "r":
        reg_user()
           
#Second statement to run assign function and write the new tasks to tasks.txt
    elif option_selct == "a":
        add_task()

#Third statement to view all tasks in system. used enumerate to list and number tasks.
    elif option_selct == "va":
        view_all()

#Forth statement for non admin users to view their tasks used """ to for easier display.            
    elif option_selct == "vm":
        view_mine()

#Fith statement for admin to view number of tasks and users in system "len"-ed the text files to determine number of lines as all entries are listed as new lines.
    elif option_selct == "tu":
        with open("user1.txt", "r+") as num_user:
            lines = num_user.readlines()
            print("There is currently {} users on the system.".format(len(lines)))
        with open("tasks1.txt", "r+") as num_tasks:
            lines = num_tasks.readlines()
            print("There is currently {} tasks on the system.".format(len(lines)))
        print()

#Sixth statement, listed and order to exicute all functions in correct order.
    elif option_selct == "gr":
        gen_report1()
        gen_report2()
        gen_report3()
        gen_report4()
        gen_report56()
        gen_report7()
        gen_report81()
        gen_report82()
        gen_report83()
        gen_report84()
        gen_report85()
        gen_report86()
        gen_report87()
        print("Reports are found in work folder.")

#Seventh staement, Calling DS
    elif option_selct == "ds":
        disp_stats()

          
#Eith statement to exit system and greet user      
    elif option_selct == "e": 
        print("Thank you, come again.")
        break
       
#Seventh statement to indicate a non-valid selection.
    else:
        print("Please make a valid selection.")
    


#Thank you, come again."
#In all honesty I am 90% Happy with this. I just could not find a better way to Display the "Each Users" tasks in DS, but personly I do prefer enumerated lists.
















          
