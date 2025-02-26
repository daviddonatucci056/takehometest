# Getting Started

## Building

### RECKON
This project uses reckon for project versioning. Commit messages control whether the version bump is patch minor or major. See https://github.com/ajoberstar/reckon for more details.

### LOMBOK
This project uses lombok. IDE users must have lombok installed before using this project.

### Running
This runs locally on port 8080.
1. You must have lombok installed. See https://projectlombok.org/ and go to installs and pick your IDE
2. Build gradle before running.
3. Run spring boot application according to your IDE.
4. Visit `/actuator/health` for health endpoint.
5. Navigate to browser. Github user data endpoint is `github-api/v1/userdata/{username}` or see swagger docs at `/swagger-ui/index.html`
6. Tests are junit 5 and should be passing.


### Decisions
First, I needed a controller and all Apis should have a version in them so I named the package v1. I separated each chunk into separate packages. Controller, services, controller advice, custom exceptions, rest client, and validation. This provides a easy way to get logically the code. The only sub package I had was the beans, when there gets to be a lot of controllers its nice to separate the beans into different folders. I separated calls into two separate services. One to hold the business logic and another to hold logic for the calling the github api. This provides a nice breakdown so if the client wants to merge other data we can add more api calls to the api service and have business validation/logic in non api service. For the rest client builder, I wanted to extract the rest client into its own class so that we didn't have to duplicate calls for similar requests. Last was the decision to separate the vendors (github) response object with the response bean b 

I did wonder if it was a mistake that avatar3 was in the url instead of avatar, and validated that it was a real url. But I would have probably gone back to the client to affirm that this was the correct url they wanted to use, because the data coming back from the service is not that url. I have choosen to not include the 3 as I would like to have confirmed that is the real url before coding it and if it was confirming what the correct number was. (I have no way of determining what business logic mapping that would be. Always use 3? 0-8 are valid)

This whole project took just over 5 hours and a little extra to document. I didn't spend time with the configuration but in the future would have set up profiles along with a default and a configuration class to manage some of those dependencies. Also would have extracted the null json check into a json utility class that would provide that functionality as it would most likely be used other places.



