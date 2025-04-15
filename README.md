# Animal Info App – NIT3213 Final Assignment

This Android app was developed for the NIT3213 Final Assignment.
It demonstrates API integration, clean Kotlin architecture, and dependency injection using Koin.

It includes three screens: **Login**, **Dashboard**, and **Details**
and interacts with the official `vu-nit3213-api` to authenticate users and display animal information.

---

## Features

### Login Screen
- Accepts username and student ID (e.g., `Shristi` / `s4678657`)
- Sends POST request to `/sydney/auth`
- Displays Toast on login success and navigates to Dashboard
- Shows error Toast for failed login

### Dashboard Screen
- Uses keypass from login to call `/dashboard/{keypass}`
- Loads entity list in a RecyclerView
- Displays species and scientific name only (summary)
- Clicking an item opens Details screen
- Includes a **Logout** button to return to login

### Details Screen
- Displays full entity data:
    - Species, scientific name, habitat, diet, conservation status, lifespan, and description
- Uses scrollable Material Design layout

---

## Technologies Used

- **Kotlin**
- **Retrofit** (API integration)
- **Koin** (Dependency Injection)
- **Coroutines** (Async processing)
- **RecyclerView**
- **Material 3 Design**
- **MockK + JUnit** (Unit testing)

---

## Login Credentials (Demo)

Use these credentials to log in:

- **Username:** `Shristi`
- **Password:** `s4678657`

> This returns `keypass = animals` and loads real data from the API

---

## How to Run the App

1. Open this project in **Android Studio**
2. Ensure **minimum SDK = 31**
3. Sync Gradle and build the project
4. Click '▶' or 'run' to run the app on emulator or device

---

## Unit Testing

Unit testing was performed using **MockK** and **JUnit**.

- Test file: `ApiServiceTest.kt`
- Test goal: verify login API returns correct keypass
- Test status: passed (mocked API response)

---

## Git Usage

This project was version-controlled using Git.  
Meaningful commit history was maintained throughout development:

Examples:
- `"Integrated Koin for DI"`
- `"Added unit test for login API"`
- `"Added Logout button to Dashboard"`

---

## Project Organization

All Kotlin files are inside: com.example.nit3213app/

---

## Notes

- Used `enableEdgeToEdge()` and ConstraintLayout for modern Material 3 design
- Fonts used: **Poppins**
- App works offline-friendly for layout testing
- API: `https://nit3213api.onrender.com`

---

## Author

**Shristi Tuladhar**  
Student ID: `s4678657`  
Course: NIT3213 – Mobile Application Development  
Lecturer: Dr. Madiha Anjum

---

## Image & Icon Credits

- **Login Screen Icon**:  
  *"Login icon"* by Freepik – [Flaticon](https://www.flaticon.com/free-icon/login_4496184)

- **Login Screen Background Image**:  
  *"Wild Cartoon American Animal Characters Group"* – [PNGTree](https://png.pngtree.com/png-vector/20240723/ourmid/pngtree-wild-cartoon-american-animal-characters-group-png-image_12954309.png)



