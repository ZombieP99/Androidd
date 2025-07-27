# Android Demo Project - Yousef

A comprehensive Android application demonstrating various Android development concepts and UI components.

## Features Demonstrated

### Layout Types
- **LinearLayout**: Used for vertical arrangement of components in main activity
- **RelativeLayout**: Used for header section with logo and title positioning
- **ConstraintLayout**: Used for user input section with flexible positioning

### Activities and Navigation
- **MainActivity**: Main screen with user input and settings
- **SecondActivity**: Secondary screen showing passed data
- **Intent Navigation**: Data passing between activities using Intent extras

### UI Components
- **TextView**: Welcome messages and labels
- **EditText**: User name input field
- **Button**: Various action buttons (Save, Navigate, Clear Data, etc.)
- **ImageView**: App logo display
- **CheckBox**: Notification settings toggle
- **Switch**: Dark mode toggle

### User Interactions
- **setOnClickListener**: Implemented on all interactive elements
- **Toast Messages**: Success/info messages and user feedback
- **AlertDialog**: Confirmation dialog for data clearing

### Java Programming Concepts
- **if-else conditions**: Used for input validation and settings logic
- **switch statements**: Used for different user greeting messages
- **Data validation**: Name input validation and null checks

### Data Persistence
- **SharedPreferences**: Saves and retrieves:
  - User name
  - Notification preferences
  - Dark mode settings

### UI Styling
- **Custom colors**: Defined color palette for consistent theming
- **Background colors**: Different backgrounds for cards and sections
- **Spacing and margins**: Proper spacing between UI elements
- **Custom logo**: SVG-based app logo in header
- **Elevation**: Card-like appearance with shadows

## Project Structure

```
app/src/main/
├── java/com/example/yousef/
│   ├── MainActivity.java          # Main activity with user input
│   └── SecondActivity.java        # Secondary activity showing data
├── res/
│   ├── layout/
│   │   ├── activity_main.xml      # Main activity layout
│   │   └── activity_second.xml    # Second activity layout
│   ├── drawable/
│   │   └── app_logo.xml           # Custom app logo
│   ├── values/
│   │   ├── colors.xml             # Color definitions
│   │   └── strings.xml            # String resources
│   └── ...
└── AndroidManifest.xml            # App configuration
```

## How to Use

1. **Enter Your Name**: Type your name in the input field and tap "Save Name"
2. **Adjust Settings**: Toggle notifications and dark mode preferences
3. **Navigate**: Tap "Go to Second Screen" to see your data on the next screen
4. **View Data**: The second screen displays your name and current settings
5. **Clear Data**: Use "Clear Data" button to reset all saved information

## Technical Requirements

- **Minimum SDK**: API 24 (Android 7.0)
- **Target SDK**: API 36
- **Language**: Java
- **Build System**: Gradle with Kotlin DSL

## Dependencies

- AndroidX AppCompat
- Material Design Components
- ConstraintLayout

This project serves as a comprehensive example of Android development fundamentals, showcasing best practices for UI design, user interaction, and data management.