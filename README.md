# STRING TO NUMBERS CALCULATOR KATA

This is a tool to extract numbers from a given strings

# HOW TO USE

You can introduce a string using a standard comma delimer or a line break \n
For example: 4,5,2\n

However we cannot use both delimiter at a time
For example : 4,5,\n2 is not allowed

You can use your own custom delimiter by adding between // and \n
For example: //X\n5X2

Negative numbers are not allowed
For example: -5,3 is not allowed 

# SETUP

Build your project using maven
cd path_of_your_project
mvn clean install
java -jar the_jar_artifact