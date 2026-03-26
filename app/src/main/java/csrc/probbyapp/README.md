# AI Usage Documentation - Uni Project

This file documents the assistance provided by AI during the development of the ProbbyApp project.

## Log of AI Contributions

### 1. Project Configuration & Sync Fix
- **Issue:** `Failed to find Platform SDK with path: platforms;android-35`
- **AI Action:** Analyzed the `build.gradle.kts` and environment. Updated the project configuration to use a compatible SDK version (34) to resolve sync errors.
- **Date:** 2026-03-04

### 2. Geocoding API Compatibility (API 33+)
- **Issue:** `getFromLocationName` was deprecated/changed in API 33, requiring a `GeocodeListener`.
- **AI Action:** Provided a modern asynchronous implementation using `GeocodeListener` for newer devices while maintaining backward compatibility for older versions.
- **Date:** 2026-03-04

### 3. UI Theme & Button Styling
- **Issue:** Buttons were remaining the default purple color despite theme changes.
- **AI Action:** 
    - Updated `activity_login_page.xml` to use theme attributes (`?attr/colorPrimary`).
    - Synchronized `values/themes.xml` and `values-night/themes.xml` to ensure consistent branding across Light and Dark modes.
    - Updated both Material Components and Material 3 base themes to ensure color overrides were applied correctly.
- **Date:** 2026-03-12

### 4. Unit Testing for User Data Handler
- **Requirement:** Create unit tests for `UserDataHandler` to verify Firestore interactions.
- **AI Action:** 
    - Added Mockito dependencies to `build.gradle.kts`.
    - Refactored `UserDataHandler.java` to allow for database injection (changing `db` visibility to public for testing).
    - Created `UserTests.java` using JUnit and Mockito to verify that `addUser` and `getUser` correctly interact with the Firebase Firestore API.
- **Date:** 2026-03-12
