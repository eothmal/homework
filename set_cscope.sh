#!/bin/bash
mkdir -p ~/.vim/plugin && \
cp /workspace/homework/cscope_maps.vim ~/.vim/plugin/. && \
cd /workspace/homework && \
cscope -b
