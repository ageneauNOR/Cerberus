/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.redcats.tst.entity;

import java.util.List;

/**
 * @author bcivel
 */
public class TCExecution {

    private long id;
    private String test;
    private String testCase;
    private String build;
    private String revision;
    private String environment;
    private String country;
    private String browser;
    private long start;
    private long end;
    private String controlStatus;
    private String controlMessage;
    private Application application;
    private String ip; // Host the Selenium IP
    private String url;
    private String port; // host the Selenium Port
    private String tag;
    private String finished;
    private int verbose;
    private String status;
    private String crbVersion;
    /**
     * From here are data outside database model.
     */
    private String environmentData;
    private Invariant environmentDataObj;
    private Invariant CountryObj;
    private int screenshot;
    private String outputFormat;
    private TCase tCase;
    private List<TCase> PreTCase;
    private CountryEnvParam countryEnvParam;
    private CountryEnvironmentApplication countryEnvironmentApplication;
    private boolean manualURL;
    private String myHost;
    private String myContextRoot;
    private String myLoginRelativeURL;
    private String seleniumIP;
    private String seleniumPort;
    private List<TestCaseStepExecution> testCaseStepExecutionList; // Host the list of Steps that will be executed (both pre tests and main test)
    private List<TestCaseExecutionData> testCaseExecutionDataList; // Host the full list of data calculated during the execution.
    private MessageGeneral resultMessage;

    public Invariant getCountryObj() {
        return CountryObj;
    }

    public void setCountryObj(Invariant CountryObj) {
        this.CountryObj = CountryObj;
    }

    public Invariant getEnvironmentDataObj() {
        return environmentDataObj;
    }

    public void setEnvironmentDataObj(Invariant environmentDataObj) {
        this.environmentDataObj = environmentDataObj;
    }
    
    public String getEnvironmentData() {
        return environmentData;
    }

    public void setEnvironmentData(String environmentData) {
        this.environmentData = environmentData;
    }

    public boolean isManualURL() {
        return manualURL;
    }

    public void setManualURL(boolean manualURL) {
        this.manualURL = manualURL;
    }

    public String getMyHost() {
        return myHost;
    }

    public void setMyHost(String myHost) {
        this.myHost = myHost;
    }

    public String getMyContextRoot() {
        return myContextRoot;
    }

    public void setMyContextRoot(String myContextRoot) {
        this.myContextRoot = myContextRoot;
    }

    public String getMyLoginRelativeURL() {
        return myLoginRelativeURL;
    }

    public void setMyLoginRelativeURL(String myLoginRelativeURL) {
        this.myLoginRelativeURL = myLoginRelativeURL;
    }

    public String getOutputFormat() {
        return outputFormat;
    }

    public void setOutputFormat(String outputFormat) {
        this.outputFormat = outputFormat;
    }

    public int getScreenshot() {
        return screenshot;
    }

    public void setScreenshot(int screenshot) {
        this.screenshot = screenshot;
    }

    public MessageGeneral getResultMessage() {
        return resultMessage;
    }

    public void setResultMessage(MessageGeneral resultMessage) {
        this.resultMessage = resultMessage;
        if (resultMessage != null) {
            this.setControlMessage(resultMessage.getDescription());
            this.setControlStatus(resultMessage.getCodeString());
        }
    }

    public List<TestCaseStepExecution> getTestCaseStepExecutionList() {
        return testCaseStepExecutionList;
    }

    public void setTestCaseStepExecutionList(List<TestCaseStepExecution> testCaseStepExecutionList) {
        this.testCaseStepExecutionList = testCaseStepExecutionList;
    }

    public String getSeleniumIP() {
        return seleniumIP;
    }

    public void setSeleniumIP(String seleniumIP) {
        this.seleniumIP = seleniumIP;
    }

    public String getSeleniumPort() {
        return seleniumPort;
    }

    public void setSeleniumPort(String seleniumPort) {
        this.seleniumPort = seleniumPort;
    }

    public CountryEnvParam getCountryEnvParam() {
        return countryEnvParam;
    }

    public void setCountryEnvParam(CountryEnvParam countryEnvParam) {
        this.countryEnvParam = countryEnvParam;
    }

    public CountryEnvironmentApplication getCountryEnvironmentApplication() {
        return countryEnvironmentApplication;
    }

    public void setCountryEnvironmentApplication(CountryEnvironmentApplication countryEnvironmentApplication) {
        this.countryEnvironmentApplication = countryEnvironmentApplication;
    }

    public TCase gettCase() {
        return tCase;
    }

    public void settCase(TCase tCase) {
        this.tCase = tCase;
    }

    public Application getApplication() {
        return application;
    }

    public void setApplication(Application application) {
        this.application = application;
    }

    public String getBrowser() {
        return browser;
    }

    public void setBrowser(String browser) {
        this.browser = browser;
    }

    public String getBuild() {
        return build;
    }

    public void setBuild(String build) {
        this.build = build;
    }

    public String getControlMessage() {
        return controlMessage;
    }

    public void setControlMessage(String controlMessage) {
        this.controlMessage = controlMessage;
    }

    public String getControlStatus() {
        return controlStatus;
    }

    public void setControlStatus(String controlStatus) {
        this.controlStatus = controlStatus;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getCrbVersion() {
        return crbVersion;
    }

    public void setCrbVersion(String crbVersion) {
        this.crbVersion = crbVersion;
    }

    public long getEnd() {
        return end;
    }

    public void setEnd(long end) {
        this.end = end;
    }

    public String getEnvironment() {
        return environment;
    }

    public void setEnvironment(String environment) {
        this.environment = environment;
    }

    public String getFinished() {
        return finished;
    }

    public void setFinished(String finished) {
        this.finished = finished;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public String getPort() {
        return port;
    }

    public void setPort(String port) {
        this.port = port;
    }

    public String getRevision() {
        return revision;
    }

    public void setRevision(String revision) {
        this.revision = revision;
    }

    public long getStart() {
        return start;
    }

    public void setStart(long start) {
        this.start = start;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }

    public String getTest() {
        return test;
    }

    public void setTest(String test) {
        this.test = test;
    }

    public String getTestCase() {
        return testCase;
    }

    public void setTestCase(String testCase) {
        this.testCase = testCase;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public int getVerbose() {
        return verbose;
    }

    public void setVerbose(int verbose) {
        this.verbose = verbose;
    }

    public List<TCase> getPreTCase() {
        return PreTCase;
    }

    public void setPreTCase(List<TCase> PreTCase) {
        this.PreTCase = PreTCase;
    }

    public List<TestCaseExecutionData> getTestCaseExecutionDataList() {
        return testCaseExecutionDataList;
    }

    public void setTestCaseExecutionDataList(List<TestCaseExecutionData> testCaseExecutionDataList) {
        this.testCaseExecutionDataList = testCaseExecutionDataList;
    }

}