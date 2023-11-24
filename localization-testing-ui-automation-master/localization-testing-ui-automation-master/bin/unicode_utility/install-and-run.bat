@echo off

echo Installing dependencies...
for /f %%i in ('python -c "import sys; print(sys.executable)"') do set "python_path=%%~i"
"%python_path%" -m pip install -r requirements.txt

if %errorlevel% neq 0 (
    echo Error: Failed to install dependencies. Exiting...
    pause
    exit /b %errorlevel%
)

echo Dependencies installed successfully.
echo Running unicode_utility.py...
"%python_path%" unicode_utility.py

timeout /t 10 /nobreak
