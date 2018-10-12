## Setting the project up in IntelliJ

1. Go to *File -> Project Structure -> Modules* and press + 
2. add *src/backend* and *src/frontend* **NOT** *backend* and *frontend*
3. press *frontend* module and go to dependencies tab and add *backend* as a dependency
4. set *frontend/resources* as resources folder
5. If you still see errors, run *File -> Invalidate Caches / Restart*