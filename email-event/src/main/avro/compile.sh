#!/bin/bash

# -string is very necessary, otherwise the key of map will be Utf8 internal class. You cannot find object with string.

java -jar /home/steve/tool/avro-tools-1.8.2.jar compile -string schema EmailCreatedEvent.avsc .
java -jar /home/steve/tool/avro-tools-1.8.2.jar compile -string schema EmailDeletedEvent.avsc .
java -jar /home/steve/tool/avro-tools-1.8.2.jar compile -string schema EmailUpdatedEvent.avsc .

# Update events to implement from EmailEvent interface in order to group these events in streams processing.
find . -name '*Event.java' -exec sed -i "s/implements org.apache.avro.specific.SpecificRecord/implements EmailEvent/g" {} +

# move to the right directory and remove the generated folder.

mv com/networknt/email/EmailCreatedEvent.java ../java/com/networknt/email
mv com/networknt/email/EmailDeletedEvent.java ../java/com/networknt/email
mv com/networknt/email/EmailUpdatedEvent.java ../java/com/networknt/email

rm -rf com
