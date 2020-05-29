# Github Android client

This project is the implementation of a hiring technical test for Datadog.

## Technical test

#### Overview

- Create an iOS or Android application that allows a user to login via Github. You can use any tools or libraries you deem useful.
- Display a list of the public repositories for the said user
- Allow the user to select a repository
- Display in the application the key statistics as well as a history of the number issue over the past year in 1 week intervals for the selected repository. We’d suggest a graphical representation like a timeseries. Make it easy for the end-user to picture the situation!
- Explain how you'd improve on this application design

#### Deliverable

- Your solution should be returned as an archive of the code.
- It should include a README that contains the instructions on how to run the application and configure it if needed
- The application should not be published to the App Store / Play Store

## ⚠️⚠️⚠️⚠️⚠️ To not forget ⚠️⚠️⚠️⚠️⚠️

- [ ] Unit tests
- [ ] Comments
- [ ] Complete this README.md with architecture explanations
- [ ] Remove this section
- [ ] Explain clean archi dependencies (schema)
- [ ] Explain classic request flow (schema)
- [ ] Explain navigation
- [ ] Explain role of each part of the Clean Archi (why a VM, why a controller, etc...)
- [ ] What could be improved: ViewModel livedata can we written by the Activity, should be accessible read-only

## How could I improve the design:

#### Architecture

Do I really need Clean Architecture for this small project?

Probably not, but I wanted to show how an example of architecture which allow large amount of features without loosing maintainability.

But, for a smaller project, I could simplify it by using a simpler MVP or MVVM architecture.

#### Design System

An easy way to change the UI would be to separate all UI components in a dedicated module.

Then, I can easily reuse them where I need.