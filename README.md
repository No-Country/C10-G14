# :weight_lifting: World Fit :woman_cartwheeling:

Application to manage flow of activities in a gym. The main features it includes are user registration and login, role-based access to resources, custom routines built with predetermined exercises, integrated payment system, and management and visualization tools for administrators.

## Meet the Team
#### At the front end:
  - <a href="https://github.com/solmicielo"> Soledad <a>
  - <a href="https://github.com/FacuIbars"> Facundo <a>
  - <a href="https://github.com/jcda23"> Juan <a>
  
#### At the back end:
  - <a href="https://github.com/DiegoHaczek"> Diego <a>
  - <a href="https://github.com/gabrielalfredoboaglio"> Gabriel <a>
  - <a href="https://github.com/EmilianoEscobedo"> Emiliano <a>

## GIT STANDARDS

### FORMAT
- Always create the branch from develop
- The branch name format is: `feature/{jiraTicket#}`.
  - I.e: `feature/SP2-WF70`<br><br>
- The pull request title format is: `{jiraTicket#}: {jiraTitle}`. 
  - I.e: `SP2-WF70: Agregar a entidad usuario PESO, ALTURA, SEXO y EDAD`.<br><br>
- The commits format is: `{jiraTicket#}: {commitDescription}`, Small commits are a nice to have. <br>
  - I.e: `SP2-WF70: Construido authDtoMapper`.<br><br>
- The pull request has to contain only the changes related to the scope defined in the ticket.
- Pull request should always be from your current branch to develop.


### BRANCHES
In the current repository you will see three diferent branches
- `master` -> this branch is only for productive versions, it has official release history.
- `develop` -> this branch serves as an integration branch for features. All features must start from this branch and after it's finished it gets merged back into develop.

For understanding more about git and how to work with different branches, I recommend to read about Gitflow workflow. [Here](https://www.atlassian.com/git/tutorials/comparing-workflows/gitflow-workflow) you have a little explanation that can serve as introduction.

