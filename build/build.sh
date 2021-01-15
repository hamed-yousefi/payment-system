#!/bin/bash

sh discovery-server/build/build.sh
sh payment-provider1/build/build.sh
sh payment-provider2/build/build.sh
sh sms-provider/build/build.sh
sh payment-gateway/build/build.sh