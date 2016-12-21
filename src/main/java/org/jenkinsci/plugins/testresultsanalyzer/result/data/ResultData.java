package org.jenkinsci.plugins.testresultsanalyzer.result.data;

import hudson.tasks.test.TabulatedResult;
import hudson.tasks.test.TestObject;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public abstract class ResultData {

	private String name;
	private boolean isConfig = false;
	private transient TabulatedResult packageResult;
	private int totalTests;
	private int totalFailed;
	private int totalPassed;
	private int totalSkipped;
	private float totalTimeTaken;
	private String status;
    private String url;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public boolean isConfig() {
		return isConfig;
	}

	public void setConfig(boolean config) {
		isConfig = config;
	}

	public TabulatedResult getPackageResult() {
		return packageResult;
	}

	public void setPackageResult(TabulatedResult packageResult) {
		this.packageResult = packageResult;
	}

	public int getTotalTests() {
		return totalTests;
	}

	public void setTotalTests(int totalTests) {
		this.totalTests = totalTests;
	}

	public int getTotalFailed() {
		return totalFailed;
	}

	public void setTotalFailed(int totalFailed) {
		this.totalFailed = totalFailed;
	}

	public int getTotalPassed() {
		return totalPassed;
	}

	public void setTotalPassed(int totalPassed) {
		this.totalPassed = totalPassed;
	}

	public int getTotalSkipped() {
		return totalSkipped;
	}

	public void setTotalSkipped(int totalSkipped) {
		this.totalSkipped = totalSkipped;
	}

	public float getTotalTimeTaken() {
		return totalTimeTaken;
	}

	public void setTotalTimeTaken(float totalTimeTaken) {
		this.totalTimeTaken = totalTimeTaken;
	}

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    //Used for constructing mock object
    public ResultData()
    {

    }

    public ResultData(TestObject result, String url) {
        setName(result.getName());
        setTotalTests(result.getTotalCount());
        setTotalFailed(result.getFailCount());
        setTotalPassed(result.getPassCount());
        setTotalSkipped(result.getSkipCount());
        setTotalTimeTaken(result.getDuration());
        setUrl(url);
        evaluateStatus();
    }

	protected void evaluateStatus() {
		if (totalSkipped == totalTests) {
			status = "SKIPPED";
		}
		else if (totalFailed == 0) {
			status = "PASSED";
		}
		else {
			status = "FAILED";
		}
	}

	public String getStatus() {
		return status;
	}

	public JSONObject getJsonObject() {
		JSONObject json = new JSONObject();
		json.put("totalTests", totalTests);
		json.put("totalFailed", totalFailed);
		json.put("totalPassed", totalPassed);
		json.put("totalSkipped", totalSkipped);
		json.put("totalTimeTaken", totalTimeTaken);
		json.put("status", status);
        json.put("url", url);
		return json;
	}

}
