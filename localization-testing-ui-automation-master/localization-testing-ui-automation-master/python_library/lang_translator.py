import sys
sys.stdout.reconfigure(encoding='utf-8')
from deep_translator import GoogleTranslator
def deeptranslatorLang(fromLanguageName, toLanguageName, text):
    try: 
        translated = GoogleTranslator(source=fromLanguageName, target=toLanguageName).translate(text)
        print(translated)
    except Exception as e: 
        print(e)
        print (">>>>>>>>>>>>>>>>>>>>An error occurred>>>>>>>>>>>>>>>>>")
arg1 = sys.argv[1]
arg2 = sys.argv[2]
arg3 = sys.argv[3]
deeptranslatorLang(arg1,arg2,arg3)

