# TestingUtilities
Utilities to help with Software QA

##TestGenerator Utility:
This utility will generate a TestSuite containing test cases based on the test's independent "Aspects" and provide a coverage statistic that tells you how much of all possible inputs (Combinations of all valid input) are being tested in the TestSuite.

If you supply a TestAspect with the set of valid inputs groups by functional similarity of programmatic processing then the TestCases generated will not be the set of all valid input. Instead, the utility will choose a subset of all valid input so that all functional groups are exercised, but will not include all possible combinations. You can specify a percent of coverage and the utility will choose a set of TestCases that exercise that percent of combinations of all valid test inputs making sure that all groups are touched.