Feature: User can log in with valid username/password-combination

    Scenario: user can login with correct password
       Given command login is selected
       When username "lur" and password "lurulur15" are entered
       Then system will respond with "logged in"

    Scenario: nonexistent user can not login to 
        Given command login is selected
        When username "liero" and password "lieroliero7" are entered
        Then system will respond with "wrong username or password"