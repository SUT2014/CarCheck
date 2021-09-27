# CarCheck

CarCheck is a BDD Cucumber style Test application written in Java which retrieves Vehicle information for a given list of Vehicle Registration Numbers.  It uses Selenium to retrieve details by comparing the list of Vehicle Registration Numbers against a well known database.
Typical Requirements
- Read Input files and retrieve Registration Numbers. Multiple test input files may be generated.
- Run Selenium based tests to verify test data.
- Retrieve Vehicle details.

# Features!

- Retrieves Car Registration Number from input file.  Multiple input files are supported.  There is no specific format for this input file as long as the Registration Numbers are mentioned.
- Uses REGEX to retrieve the Registration Numbers, REGEX string is configurable as well in the config.properties project file.
- Using Car Regs from input file, retrieves Tax details from well known website, in this case it is https://cartaxcheck.co.uk/ .  This can be configured in the config.properties file.
- The Vehicle tax details are logged, asserts are currently disabled.

TaxMan has been developed using the following tools/apps:

* [Java]     - Core Java
* [Log4j2]   - Logging utility by Apache.
* [IntelliJ] - IDE
* [Selenium] - Firefox Webdriver
* [Cucumber] - Cucumber Packages(io.cucumber)
* [JUnit]    - JUnit Framework


And of course TaxMan itself is open source.

### Installation

Use Maven to build CarCheck.  the pom file can be found along with the source.
Typical Maven commands:
-- mvn clean test


input files:

# car_input.txt
--sample content--

There are multiple websites avaiable to check current car value in United Kingdom.The best of all is webuyanycar.com for intant valuation.The other examples are autotrader.com and confused.com. Checking example BMW with registration DN09HRM the value of the car is roughly around £3000. However car with registration BW57 BOW is not worth much in current market. There are multiple cars available higher than £10k with registraions KT17DLX andSG18 HTN.

# car_output.txt
REGISTRATION,MAKE,MODEL,COLOR,YEAR
SG18HTN,Volkswagen,Golf Se Navigation Tsi Evo,White,20189
DN09HRM,BMW,320D Se,Black,2009
BW57BOF,Toyota,Yaris T2,Black,2010
KT17DLX,Skoda,Superb Sportline Tdi S-A,White,2017


**Free Software, Hell Yeah!**
