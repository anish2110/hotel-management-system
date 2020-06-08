Admin username –  admin
Admin password –   admin123







Key Features – 

1.	Menu with 5 options –
1.1.	Login
1.2.	Admin Login
1.3.	SignUp
1.4.	Book Hotel
1.5.	Exit


2.	Login –
2.1.	Username – error if wrong username input, asks to try again
2.2.	Password – error if wrong password input, asks to try again
2.3.	Opens booking interface


3.	Admin Login – 
3.1.	Username – error if wrong username input, asks to try again 
3.2.	Password – error if wrong password input, asks to try again

3.3.	Admin Menu –
3.3.1.	Add City
3.3.2.	Add Hotel
3.3.3.	Check Booking history
3.3.4.	Exit

3.4.	Add City –
3.4.1.	Enter city name – error if city already exists, asks if to add hotel or exit
3.4.2.	Enter number of hotels – error if wrong input, such as anything other than int, asks to retry
3.4.3.	Enter hotel name
3.4.4.	Enter no of room type - error if wrong input, such as anything other than int, asks to retry
3.4.5.	Enter room type
3.4.6.	Enter no of rooms
3.4.7.	Enter price
3.4.8.	Back to admin menu

3.5.	Add Hotel –
3.5.1.	Enter city name 
3.5.2.	Enter number of hotels – error if wrong input, such as anything other than int, asks to retry
3.5.3.	Enter hotel name
3.5.4.	Enter no of room type - error if wrong input, such as anything other than int, asks to retry
3.5.5.	Enter room type
3.5.6.	Enter no of rooms
3.5.7.	Enter price
3.5.8.	Back to admin menu

3.6.	Check Booking History – 
3.6.1.	Enter username – error if no username with input exists, asks to retry
3.6.2.	Back to Admin menu

3.7.	Exit – back to Main Menu

4.	SignUp –
4.1.	Enter name
4.2.	Enter username – error if username already exists, asks to retry
4.3.	Enter password
4.4.	Enter DOB – error, if input date format is wrong or illegal date is entered (YYYY-MM-DD)
4.5.	Enter mobile number 
4.6.	Enter email 
4.7.	Opens booking interface

5.	Book Hotel –
5.1.	Enter city – error, if city database doesn’t exists, asks choices
5.2.	Select hotel – error, if wrong input entered, asks to retry
5.3.	Select room type - error, if wrong input entered, asks to retry
5.4.	Enter no of rooms – error, if wrong input or input is larger than required
5.5.	Enter check in date – error, if wrong date format or illegal dates or date before current date
5.6.	Enter check out date - error, if wrong date format or illegal dates entered

5.7.	Confirm booking – error if wrong input, asks to retry
5.7.1.	Yes – confirms booking, opens Main Menu List
5.7.2.	No – 
5.7.2.1.	City – opens city interface
5.7.2.2.	Exit – Main Menu

6.	Exit – exits the program
