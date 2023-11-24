"""
    Unicode Utility
"""
import os
import yaml
from datetime import datetime


def load_config(config_file_path='config/config.yml'):
    """
    Load the configuration from the YAML config file.

    Args:
        config_file_path (str): The path to the configuration file.

    Returns:
        dict: A dictionary containing the configuration values.
    """
    with open(config_file_path, 'r', encoding='utf-8') as f:
        return yaml.safe_load(f) or {}


def read_language_config(language, config_file_path='config/config.yml'):
    """
    Read the language-specific configuration from a separate YAML file.

    Args:
        language (str): The language for which to read the configuration.
        config_file_path (str): The path to the main configuration file.

    Returns:
        dict: A dictionary containing the language-specific configuration.
    """
    language_config_path = os.path.join(os.path.dirname(config_file_path), language + '.yml')
    try:
        lang_configset = load_config(language_config_path)
        return lang_configset
    except FileNotFoundError:
        print(f"Language configuration file for {language} not found: {language_config_path}")
        return {}


def generate_unicode(text):
    """
    Generate Unicode representations for characters in a given text.

    Args:
        text (str): The input text.

    Returns:
        str: The Unicode representations of characters in the input text.
    """
    return ''.join([generate_char_unicode(char) for char in text])


def generate_char_unicode(char):
    """
    Generate a Unicode representation for a given character.

    Args:
        char (str): The input character.

    Returns:
        str: The Unicode representation of the input character.
    """
    return ''.join([f"\\u{ord(char):04X}" for char in char])


def process_file(input_filename, output_filename, lang_config):
    """

    :param input_filename:
    :param output_filename:
    :param lang_config:
    """
    with open(input_filename, 'r', encoding='utf-8') as input_file, \
            open(output_filename, 'w', encoding='utf-8') as output_file:
        output_file.write("Input\tGenerated Unicode\tValidation Result\n")  # Header
        for line in input_file:
            line = line.strip()
            if line:
                unicode_line = generate_unicode(line)
                # Perform validation and determine the result
                validation_result = "Valid" if line == generate_word_from_unicode(unicode_line, lang_config) else "Invalid"
                output_file.write(f"{line}\t{unicode_line}\t{validation_result}\n")


def validate_output(input_filename, output_filename, lang_config):
    """

    :param input_filename:
    :param output_filename:
    :param lang_config:
    """
    validation_failed = False  # Flag to track if any validation failures occur
    with open(input_filename, 'r', encoding='utf-8') as input_file, \
            open(output_filename, 'r', encoding='utf-8') as output_file:
        for line, output_line in zip(input_file, output_file):
            input_line = line.strip()
            output_line = output_line.strip().split('\t')[1]  # Extract the Unicode representations

            # Convert Unicode back to text using the reverse process
            reversed_text = generate_word_from_unicode(output_line, lang_config)

            # Compare the reversed text with the original input
            if input_line != reversed_text:
                validation_failed = True
                print(f"Validation failed for line: {input_line}")
                print(f"Generated Unicode: {output_line}")
                print(f"Reversed Text: {reversed_text}")
                print("---")

    if not validation_failed:
        print("Validation successful. All Unicode representations match the original input.")


def generate_word_from_unicode(unicode_text, lang_config):
    """
    Generate the original text from Unicode representation.

    Args:
        unicode_text (str): The Unicode representation.
        lang_config (dict): The language configuration.

    Returns:
        str: The original text.
    """
    # Split the input Unicode text by escape sequences "\\uXXXX"
    unicode_parts = unicode_text.split('\\u')[1:]
    # Convert each Unicode part back to a character and join them to form the original text
    original_text = ''.join([generate_char_from_unicode('\\u' + part, lang_config) for part in unicode_parts])
    return original_text


def generate_char_from_unicode(unicode_part, lang_config):
    """
       Generate the original character from Unicode representation.

       Args:
           unicode_part (str): The Unicode representation part.
           lang_config (dict): The language configuration.

       Returns:
           str: The original character.
       """
    # Split Unicode representation by escape sequence "\\uXXXX"
    char_parts = unicode_part.split('\\u')[1:]

    # Convert each Unicode part back to a character and join them to form the original character
    original_char = ''.join([chr(int(char_part, 16)) for char_part in char_parts])

    return original_char


def get_timestamp():
    """
    Get the current timestamp in a formatted string.

    Returns:
        str: Formatted timestamp string.
    """
    now = datetime.now()
    return now.strftime("%Y%m%d%H%M%S")


def update_config_selected_language(config, selected_language):
    """

    :param config:
    :param selected_language:
    """
    config['selected_language'] = selected_language
    input_file_name = f"{selected_language}.txt"
    config['Input_file_name'] = input_file_name


if __name__ == "__main__":
    try:
        config = load_config()
        input_folder_location = config.get('input_folder_location', 'input')
        output_folder_location = config.get('output_folder_location', 'output')

        # Prompt the user for language selection
        print("Select a language:")
        print("1. Hindi")
        print("2. English")
        print("3. Chinese")
        print("4. French")
        selected_language_index = input("Enter the index of the language: ")

        # Map the selected index to a language
        language_mapping = {
            '1': 'Hindi',
            '2': 'English',
            '3': 'Chinese',
            '4': 'French',
        }
        selected_language = language_mapping.get(selected_language_index)

        if not selected_language:
            print("Invalid language selection.")
            exit(1)

        # Update the config file with the selected language and input filename
        update_config_selected_language(config, selected_language)
        with open('config/config.yml', 'w', encoding='utf-8') as config_file:
            yaml.dump(config, config_file, default_flow_style=False, allow_unicode=True)

        # Create the output filename with a timestamp
        timestamp = get_timestamp()
        output_filename = f"output_{selected_language}_{timestamp}.txt"

        lang_config = read_language_config(selected_language)
        if not lang_config:
            exit(1)

        process_file(
            os.path.join(input_folder_location, config['Input_file_name']),
            os.path.join(output_folder_location, output_filename),
            lang_config
        )

        print(f"Unicode representations have been generated and saved to '{output_filename}'.")

    except Exception as e:
        print(f"An error occurred: {str(e)}")
