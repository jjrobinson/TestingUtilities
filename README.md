# TestingUtilities
Utilities to help with Software QA

##TestGenerator Utility:
This utility will generate a TestSuite containing test cases based on the test's independent "Aspects" and provide a coverage statistic that tells you how much of all possible inputs (Combinations of all valid input) are being tested in the TestSuite.

If you supply a TestAspect with the set of valid inputs groups by functional similarity of programmatic processing then the TestCases generated will not be the set of all valid input. Instead, the utility will choose a subset of all valid input so that all functional groups are exercised, but will not include all possible combinations. You can specify a percent of coverage and the utility will choose a set of TestCases that exercise that percent of combinations of all valid test inputs making sure that all groups are touched.

##CMS Interface File Verification
This utility will tell you if your provided file conforms to the CMS Interface File specification for that interface.  Interface file specifications are stored in .rules files which are '|' delimited and have special key-phrases for separating individual record definitions.

##License
See LICENSE file.  The gist is that...

This is a learning project that isn't expected to be useful outside of a very very very specific niche.  But just in case...... if you check it in anything here, thanks for helping, but waive good-by to all rights.  If you fork or check-out the code it is Apache 2.0.