#!/bin/bash
# find . -name '*.java' > cscope.files
# find . -name '*.html' >> cscope.files
mkdir -p ~/.vim/plugin && \
cp /workspace/homework/*.vim ~/.vim/plugin/. && \
cd /workspace/homework && \
cscope -b
cp /workspace/homework/dotvimrc ~/.vimrc

