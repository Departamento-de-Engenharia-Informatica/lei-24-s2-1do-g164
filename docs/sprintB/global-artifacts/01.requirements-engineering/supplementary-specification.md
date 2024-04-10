# Supplementary Specification (FURPS+)

## Functionality

_Specifies functionalities that:  
&nbsp; &nbsp; (i) are common across several US/UC;  
&nbsp; &nbsp; (ii) are not related to US/UC, namely: Audit, Reporting and Security._

*  All those who wish to use the application must be authenticated with a password
   of seven alphanumeric characters, including three capital letters and two digits.

* Business rules validation must be respected when recording and updating data.

* Adopt best practices for identifying requirements, and for OO software analysis 
and design.


## Usability

Evaluates the user interface. It has several subcategories,
among them: error prevention; interface aesthetics and design; help and
documentation; consistency and standards.

* The application documentation must be in English language.

* Javadoc must be used to generate useful documentation for Java code.

* All the images/figures produced during the software development process should
be recorded in SVG format.




## Reliability

_Refers to the integrity, compliance and interoperability of the software. The requirements to be considered are: frequency and severity of failure, possibility of recovery, possibility of prediction, accuracy, average time between failures._

* The application should use object serialization to ensure data persistence between
two runs of the application.

## Performance

_Evaluates the performance requirements of the software, namely: response time, start-up time, recovery time, memory consumption, CPU usage, load capacity and application availability._

* n/a

## Supportability

_The supportability requirements gathers several characteristics, such as:
testability, adaptability, maintainability, compatibility,
configurability, installability, scalability and more._

* The development team must implement unit tests for all methods, except for
methods that implement Input/Output operations.

* The JaCoCo plugin should be used
to generate the coverage report.

* The app needs to support English language.

* It should be easy to maintain and update.

* The software should have good documentation.

* The software should be compatible with future aplication updates.

## +

### Design Constraints

_Specifies or constraints the system design process. Examples may include: programming languages, software process, mandatory standards/patterns, use of development tools, class library, etc._

* The application must be developed in Java 
* The application will be developed using the IntelliJ IDE and graphical interface from JavaFX
* The unit tests should be implemented using  JUnit 5.
* The JaCoCo plugin will generate the coverage report.

### Implementation Constraints

_Specifies or constraints the code or construction of a system such
as: mandatory standards/patterns, implementation languages,
database integrity, resource limits, operating system._

*  The graphical interface needs to be developed in JavaFX.
*  The application will be developed in Java language using the IntelliJ IDE.
*  The app must support English language.
*  The development team must implement unit tests for all methods, except for methods that implement Input/Output operations.
*  The unit tests should be implemented using the JUnit 5 framework.
*  The JaCoCo plugin will generate the coverage report.
*  The team must adopt recognized coding standards (e.g., CamelCase);



### Interface Constraints

* The graphical interface needs to be developed in JavaFX.

### Physical Constraints

* n/a