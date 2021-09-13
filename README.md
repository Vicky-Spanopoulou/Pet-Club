# PetClub report technical specifications
## Description:
PetClub is a Web App designed to cover the needs for an online examination record repository for you pets!

A user can keep all of his pet's info in one place but also be able to keep up with his vet's examinations and perscriptions for his pet.
In addition he can check out businesses close to him, or post an ad of a lost pet or one he just found.

PetClub is not only for simple users. Vets can also register their business by buying a subscription package which gives them the opportunity to advertise their business.
Also it gives them the ability to keep an online record for their patients.

Other pet related businesses like pet shops, pet groomers etc can be advertised too using the PepClub.

## Technical description: 
### Back-end framework:
Java Spring boot project: Spring JPA - Security - Google guava - JWT - Javax mail - Javax validation - Web socket
### Front-end framework:
HTML5 - CSS - TypeScript - Angular 2
### Database: 
MySQL
### External Libraries:
Here map - ngx-editor - ngx-paypal - ngx-awesome-popup

## Requirements and how to run the application:
To run the application please make sure that you have installed the following:

Java 8 - node.js - MySQL

#### Steps:
1. Git clone the project from GitHub repository
2. Through the terminal, navigate to the Angular folder PetClub and type:
    - npm install
    - npm install ngx-editor
    - npm install ngx-paypal -save
    - npm i @costlydeveloper/ngx-awesome-popup
3. Create a Database with the name PetClub in your MySQL database
4. Open the application.yml (inside the src folder in Java)
    - set Database name
    - set Database username
    - set Database password
5. Email Service: Open Java app inside of constant and 
    - change your username (email)
    - and password
6. Get a Here map API key
    - https://developer.here.com/tutorials/getting-here-credentials/
7. Open angular/PetClub/src/app/map/map.component.ts and write your here map API key in line 49
8. Run Java program

## Java Roles
By registering to the PetClub web app the user has the role USER. By buying a subscription package he can take the role of ADVERTISED so he can advertise his business.
And with the proffesional subscription package he can take the role of DOCTOR.
The ADMIN role can access only the Admin Dashboard, where one can manage all of the apps features besides the vet's medical records (which can only be acceessed by the vets)

#### UNREGISTERED:
- can search for businesses near by
- send an email directly to the business
- lost and found pet ads
- and register to the PetClub and the newsletter

#### USER:
- can upload an image of his pet
- can upload an image and the info for the lost/found ad
- can add reminders (to do list)
- cab rate businesses

#### ADVERTISED:
- all of the USER's abilities plus
- he can register a business with images and business info

#### DOCTOR:
- all of the ADVERTISED's abilities plus
- he can add clients' pets to his dashboard
- and keep an examination record

#### ADMIN:
- can manage users, pets, ads, transactions, subscriptions, categories (pet, business, cities, subscriptions)
- can access the Admin's chat (web socket) whice is visible only to the app's admins

## Screenshots:
#### Landing Page:

![landing-page1](https://user-images.githubusercontent.com/76260824/132859091-9228f093-797c-4039-991d-8dbef72ab529.png)
![landing-page2](https://user-images.githubusercontent.com/76260824/132859256-cfa2d03a-3b3c-4dc4-af0e-7cf3d677dfb9.png)

#### Find a Service:

![findAservice](https://user-images.githubusercontent.com/76260824/132859586-89aaaf28-df57-428e-94ab-14c9febc38cf.png)

#### Lost & Found:

![lostANDfound](https://user-images.githubusercontent.com/76260824/132859619-d43abd1d-0573-485a-8572-fa4c7020edcf.png)
![adPage](https://user-images.githubusercontent.com/76260824/132859715-9d1e18d1-0192-4643-a42d-3ccd5cd24bf2.png)

#### Login/Register:

![login](https://user-images.githubusercontent.com/76260824/132859869-38fce68a-14e8-4a05-b710-e0bb7d14ef1c.png)

#### User profile:

![userProfile](https://user-images.githubusercontent.com/76260824/132859642-1f0fcf81-410d-45f6-9966-b3d611bde1bd.png)
![userProfile2](https://user-images.githubusercontent.com/76260824/132859653-ff8e2e74-076e-4f2e-9de1-b512d94be969.png)

#### Pet profile:

![petprofile](https://user-images.githubusercontent.com/76260824/132859670-3ad39edf-f797-4618-922e-a609e3c19898.png)

#### Business profile:

![businessProfile](https://user-images.githubusercontent.com/76260824/132868388-78eea7e2-f76d-4f4e-b122-b1de3ced7608.png)

#### Vet's dashboard:

![vet](https://user-images.githubusercontent.com/76260824/132859804-0cb8496e-fc96-440a-8e33-3b8526f49825.png)
![vet2](https://user-images.githubusercontent.com/76260824/132859812-ed9d9061-55ea-4055-a424-faab59b07c26.png)

#### Admin's dashboard:

![admin1](https://user-images.githubusercontent.com/76260824/132859900-5e8d758d-fb5a-4af1-8008-3c42235da502.png)
![admin2](https://user-images.githubusercontent.com/76260824/132859907-37acf3a5-19ec-4af2-9ff6-ad754b145712.png)
![admin3](https://user-images.githubusercontent.com/76260824/132868508-edd2532b-600c-4463-81fe-53b47f13ca94.png)

## Contributors:

### Athanasios Vavatsikos
- GitHub: https://github.com/nasosvava

- LinkedIn: https://www.linkedin.com/in/athanasios-vavatsikos-2624391b9/

### Chris Kotsou
- GitHub: https://github.com/ChristosxD

- LinkedIn: https://www.linkedin.com/in/christos-kotsou-33046b213/

### Nick Tsepetzidis
- GitHub: https://github.com/tsepeto

- LinkedIn: https://www.linkedin.com/in/nick-tsepetzidis-b37bb6126/

### Vicky Spanopoulou
- GitHub: https://github.com/Vicky-Spanopoulou

- LinkedIn: https://www.linkedin.com/in/vasiliki-spanopoulou-80182386/


This project is a result of teamwork and dedication to our goal.
We all worked hard and gave our best. Thank you guys!

