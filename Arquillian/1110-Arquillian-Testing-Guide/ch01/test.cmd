@echo off
setlocal

if [%1]==[] (
  gradle test
  goto:eof
)

if [%1]==[index] (
  explorer "build\reports\tests\test\index.html"
  goto:eof
)

gradle test --tests %*