# Earthquake Monitor

## Summary
Earthquakes are becoming more frequent, making it useful to have an app that reads earthquake data and displays it for users.  
The purpose of this app is to pull data from a feed hosted by the **USGS** for earthquakes that occurred within the last hour, updated every minute.

The "summary" data will be displayed in a **list view** with a native indicator that more details are available.  
Users can tap a "summary" row to navigate to a **detailed view**, which fetches data from a different URL endpoint.

---

## Instructions

1. Create a **native mobile app** that reads the "summary" earthquake feed for quakes occurring within the last hour on launch.
2. Each row should display:
    - **Magnitude** of the earthquake
    - **Location** of the earthquake
3. The **magnitude** row should be color-coded:
    - `0.0 – 0.9` → Green
    - `9.0 – 9.9` → Dark Red
    - Use suitable gradient colors for intermediate values.
4. Each row should include a **native indicator** to show that it is tappable for detailed information.
5. Upon tapping a row, navigate to a **detail screen**.
6. The "summary" view should:
    - Use the **title from the "summary" feed**
    - Include a **refresh button** in the navigation/action bar.

### Detail Screen
The "detail" screen must display the following textual information about the earthquake:
- Magnitude
- Date and time
- Location, including depth

---

## Criteria

- Support only **portrait orientation** on phones.
- You may use any references, including online resources.
- UI/UX should follow **platform standards** and provide a pleasant user experience.
- Be ready to discuss:
    - Your code
    - Implementation choices
    - Trade-offs made during development
- You may use **open-source third-party libraries**.
- The project **must compile without errors or warnings**.

---

## Extra Credit

1. Implement **pull-to-refresh** on the "summary" view.
2. Make the "detail" screen a **split view (horizontal)**:
    - Top view: textual information about the earthquake
    - Bottom view: map showing a **pin at the epicenter**

---

## References

- [USGS Earthquake feed format explanation](https://earthquake.usgs.gov/earthquakes/feed/v1.0/geojson.php)
- [USGS Earthquake feed for all earthquakes within the past hour](https://earthquake.usgs.gov/earthquakes/feed/v1.0/summary/all_hour.geojson)


## 🛠 Tech Stack

- **Language:** Kotlin
- **UI:** Jetpack Compose
- **Architecture:** MVVM (Model–View–ViewModel)
- **Dependency Injection:** Hilt
- **Networking:** Retrofit
- **Asynchronous:** Kotlin Coroutines & Flow
- **Local Database:** Room
- Google Map
- 
## Development Approach

- MVVM Architecture
- Feature based (layered) modularity
- Separation of concern using DI, Navigation(Route)

