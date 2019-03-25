Time To Fund Pair Programming Exercise
============================

## Intro

Welcome! This challenge is designed to give us a better idea of how you work
on a back-end project. We've found project-based challenges like this are
often more accurate than traditional coding interviews.

## The Exercise

Due to recent advances in data analytics, we've come to realize that gifs 
shared per hour is really the most important lever when it comes to improving 
time to fund so we've decided to build an internal portal for sharing gif collections!

We would like to work with you to extend one of our existing Giphy service implementations 
to add some features to improve functionality and usability. Feel free / encouraged to ask 
us any questions you have, look up anything you need, point out and fill in any blanks that might arise, 
make any suggestions you come up with, and generally engage with us as you need. 
Remember, there are no stupid questions, only interviewers who are bad at writing technical specs. Collaboration encouraged!

Best of luck!

### Current Application

- A gif search web service
- Runs on port 8080
- Uses the Giphy API and uses the Giphy public beta key
    - Giphy API documentation can be found at https://developers.giphy.com/docs/#technical-documentation
- Always return exactly 5 results or 0 results if there are less than 5 available
- Is accessed via `http://localhost:8080/search/[search term]`
- Responses is in JSON in the following format:
    ``` 
    {
        data: [
            {
            gif_id: "FiGiRei2ICzzG",
            url: "http://giphy.com/gifs/funny-cat-FiGiRei2ICzzG",
            }
        ]
    }
    ```


### Challenge

- Add a pagination token to the existing application
- Incrementing the Pagination token will return the next set of 5 results, 
    - i.e. `current-pagination-token` returns results 1-5, `next-pagination-token` returns results 6-10
- Pagination token is passed in as a query parameter  
- Is used via `http://localhost:8080/search/<search term>?page=<pagination-token>`
- This will return a JSON object in the following format:
    ```
    {
        data: [
            {
                gif_id: "FiGiRei2ICzzG",
                url: "http://giphy.com/gifs/funny-cat-FiGiRei2ICzzG",
            }
        ],
        next: "http://localhost:8080/search/<search term>?page=<next-pagination-token>"
    }
    ```

### Implementations

#### Java
- [Spring Boot](http://spring.io/projects/spring-boot) app located in `/springboot` 
- Uses [Maven](https://maven.apache.org/)
- Build:
    ```
    mvn package
    ```
- Run:
    ```
    java -jar target/giphy-search-service.jar  
    ```
- Test:
    ```
    mvn test
    ```

#### JavaScript
- [Node](https://nodejs.org) / [Express](https://expressjs.com/) app located in `/node`
- Setup:
    ```
    apt-get update
    apt-get install nodejs -y
    apt-get install npm -y
    ln -s /usr/bin/nodejs /usr/bin/node
    npm install --production
    ```
- Run:
    ```
    export port=8080
    export giphy__api_key=Sg1prFpm2kIIPlD689Zx6TfAPx80J4L6
    node index.js
    ```
- Test:
    ```
    bash scripts/test.sh
    ```    

#### Python
- [Flask](http://flask.pocoo.org/) Python 3 app located in `/flask`
- Setup:
    ```
    install python3-pip python3-dev
    pip3 install virtualenv
    
    cd flask/giphy_search
   
    virtualenv -p `which python3` venv
    source venv/bin/activate
    pip install -r requirements.txt
    ```
- Run:
    ```
     python giphy_search.py
    ```
- Test:
    ```
    Heh... about that
    ```

## Feedback

We're always looking for ways to improve our processes at SoFi so
let us know if anything is especially frustrating (or fun)!
