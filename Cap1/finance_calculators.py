

import math

#For the purpose of this excercise I have included a security code as this is the case with most financial service providers* for this the code is 1234
user_code = input("Please enter your code: ")
user_code_check = "1234"
user_name = "Jack Ma"
print()

#Running user_code to check and greet client assigned to the security code.
if user_code == user_code_check:
    print("Welcome back {}.".format(user_name))
    print()


    #Selecting calculation, Requesting decision from client
    print ("Kindly indicate what calculator you would like to use: \n"
      "Investment - to calculate the amount of interest you'll earn on investment. \n"  #the pdf wording said "to calculate the amount of interest you'll earn on interest. I changed the last "interest" to "invest" as this makes more sense.
      "Bond       - to calculate the amount you'll have to pay per month on a home loan. \n")  #I added "per month" in the wording for better clarity. 
    print()

    #making use of ".lower()" to ensure that the input is not case senisitive.
    calcu_select = input("Enter selection here: ").lower()
    print()

    #calculating and exicuting the investment section of the program. again,making use of ".lower()" to ensure that the input is not case senisitive. (also added the shortened "invest" as the user could use this)
    if calcu_select == "investment" or calcu_select == "invest":
        amount = float(input("Please enter the amount you are depositing: R "))
        interest = float(input("Please enter the percentage of interest: "))
        years = float(input("Please enter the number of years you would like to invest for: "))
        
        interest_final = float(interest/100)
        answer1 = float(amount*(1+interest_final*years))
        answer2 = float(amount*math.pow((1+interest_final),years))
        final_answer1 = (format(answer1,'.2f'))                                          #This is an extra step i used to "print" .00 decimals (ie. 123.10 insted of 123.1)*I came accros this on stack over flow for reference
        final_answer2 = (format(answer2,'.2f'))                                          #Same as above
        interest_SC = input("Please select Simple or Compound interest: ").lower()
        print()
        
        if interest_SC == "simple":
            print ("Your invenstment will total to R {} .".format(final_answer1))
        elif interest_SC == "compound":
            print ("Your invenstment will total to R {} .".format(final_answer2))
        elif interest_SC != "simple" or "interest_SC" != compound:
            print ("Wrong input, please start again and select Simple or Compound.")
    else:
        print ("Wrong input, please select Investment or Bond")
        

    #I utilized a diffrent formula example for the calculation of the monthly bond repayment from *https://www.calculatestuff.com/financial/mortgage-calculator
    if calcu_select == "bond":
        value = float(input("Please enter the present value of the property: R "))
        interest = float(input("Please enter the given interes rate: "))
        months = float(input("Over how many months would you like to settle: "))
        
        interest_percent = float(interest/100)
        answer = (float(interest_percent/12)*value)/(1-(1+interest_percent/12)**-months)  #*
        final_answer = (format(answer,'.2f'))                                             #Same as in Invest calculation, extra step to "print" .00 decimals (ie. 123.10 insted of 123.1)
        print()
        print("Your monthly bond installment will total to R {} .".format (final_answer))

   
#Security answer should the client on input the correct code
else:
    print("Invalid User Code")
print()

#greeting client
print ("Thank you come again.")

    
    
