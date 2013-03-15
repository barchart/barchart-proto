<!--

    Copyright (C) 2011-2013 Barchart, Inc. <http://www.barchart.com/>

    All rights reserved. Licensed under the OSI BSD License.

    http://www.opensource.org/licenses/bsd-license.php

-->
barchart-proto
==============

barchart protocols

Requirements:

- Install the protoc compiler before attempting anything.

	- For Windows: Download the install here:
	
		http://code.google.com/p/protobuf/downloads/list

		- Extract protoc.exe and place in a location in PATH

		- Restart Eclipse to let it grab the new PATH

	- For Linux same URL and follow instructions\

- Make sure the maven plugin is up to date in barchart-proto-buf-archon pom.xml

	Latest is:

	                    <groupId>com.github.igor-petruk.protobuf</groupId>
                        <artifactId>protobuf-maven-plugin</artifactId>
                        <version>0.6.2</version>
	