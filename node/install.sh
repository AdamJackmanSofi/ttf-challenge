#!/bin/sh

set -ex

apt-get update
apt-get install nodejs -y
apt-get install npm -y
ln -s /usr/bin/nodejs /usr/bin/node
npm install --production