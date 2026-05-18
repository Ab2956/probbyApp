READ.md for Probby - How to use application and AI Usage Documentation
How to use Application
Test User - with preset data and credentials
Email: adam@example.com
Password: test123
Create user
Using Create Account Activity: input email, password, and name
Using Login Activity: input email and password
Add Property
Using Add Property Activity: click add property button
In add property fragment: input property details location must be valid to be added to the map
Property fragment: click save button
Properties fragment: property will show up
Map
In Map fragment tab: properties will show on map with marker
In Map fragment click on property to view details
AI Usage Documentation - Uni Project
This file documents the assistance provided by AI during the development of the ProbbyApp project.

Log of AI Contributions using Gemini
1. Project Configuration & Sync Fix
Issue: Failed to find Platform SDK with path: platforms;android-35
AI Action: Analyzed the build.gradle.kts and environment. Updated the project configuration to use a compatible SDK version (34) to resolve sync errors.
Date: 2026-03-04
2. Geocoding API Compatibility (API 33+)
Issue: getFromLocationName was deprecated/changed in API 33, requiring a GeocodeListener.
AI Action: Provided a modern asynchronous implementation using GeocodeListener for newer devices while maintaining backward compatibility for older versions.
Date: 2026-03-04
3. UI Theme & Button Styling
Issue: Buttons were remaining the default purple color despite theme changes.
AI Action:
Updated activity_login_page.xml to use theme attributes (?attr/colorPrimary).
Synchronized values/themes.xml and values-night/themes.xml to ensure consistent branding across Light and Dark modes.
Updated both Material Components and Material 3 base themes to ensure color overrides were applied correctly.
Date: 2026-03-12
4. Unit Testing for User Data Handler
Requirement: Create unit tests for UserDataHandler to verify Firestore interactions.
AI Action:
Added Mockito dependencies to build.gradle.kts.
Refactored UserDataHandler.java to allow for database injection (changing db visibility to public for testing).
Created UserTests.java using JUnit and Mockito to verify that addUser and getUser correctly interact with the Firebase Firestore API.
Date: 2026-03-12
5. Material Design 3 Migration & Navigation Fixes
Issue: InflateException and Resources$NotFoundException when using Material 3 components like BottomNavigationView.
AI Action: Identified missing mandatory Material 3 theme attributes (e.g., colorSecondaryContainer, colorOnSecondaryContainer, colorSurfaceVariant). Updated themes.xml and values-night/themes.xml to provide a complete Material 3 color palette, resolving inflation crashes.
Date: 2026-05-13
6. Fragment Implementation & Lifecycle Management
Requirement: Implement efficient navigation between multiple fragments (Home, Map, Properties, etc.) in HomePageActivity.
AI Action:
Designed a show/hide fragment transaction strategy to preserve fragment state and UI positions.
Implemented restoration logic using FragmentManager tags to prevent duplicate "ghost" fragments during configuration changes (like screen rotation).
Date: 2026-03-16
7. Performance & Resource Optimization
Issue: "Invalid ID 0x00000000" crashes caused by resource resolution failures and syntax errors.
AI Action:
Conducted an audit of colors.xml and theme files to fix malformed XML and invalid resource references.
Optimized the theme hierarchy to ensure efficient attribute resolution, improving app stability and reducing layout inflation overhead.
Date: 2026-05-13
