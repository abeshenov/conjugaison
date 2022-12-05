#!/bin/bash

mongoimport --collection=verbConjugation --jsonArray --file=/mongo-dump.json
