#!/bin/bash

echo "Installing dependencies..."
python_path=$(python -c "import sys; print(sys.executable)")
$python_path -m pip install -r requirements.txt

if [ $? -ne 0 ]; then
    echo "Error: Failed to install dependencies. Exiting..."
    read -p "Press Enter to continue..."
    exit $?
fi

echo "Dependencies installed successfully."
echo "Running unicode_utility.py..."
$python_path unicode_utility.py

sleep 10
