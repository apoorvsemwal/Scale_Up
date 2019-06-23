#import nltk
import random

# nltk.download('punkt')
# nltk.download('averaged_perceptron_tagger')
# nltk.download('brown')
# nltk.download('twitter_samples')
# nltk.download('stopwords')

#from textblob import TextBlob
from random import shuffle
from textblob.classifiers import NaiveBayesClassifier
from nltk.corpus import twitter_samples
from nltk.corpus import stopwords
from nltk.tokenize import word_tokenize
from nltk import pos_tag
#from nltk.corpus import stopwords
from nltk.stem import WordNetLemmatizer
#from nltk.stem import PorterStemmer
#from sklearn.preprocessing import LabelEncoder
from collections import defaultdict
from nltk.corpus import wordnet as wn

def sentiment_analysis_result(input_tweet,classifier, *args, **kwargs):
    return 1 if classifier.classify(process_data(cleanText(input_tweet))) == "pos" else 0

def cleanText(text):
   stop_words = stopwords.words('english')
   all_words = text.split(" ")
   updated = list()
   for word in all_words:
       if word not in stop_words:
           updated.append(word)
   text = ' '.join(updated)
   return text

def process_data(text):
   text = word_tokenize(text)
   tag_map = defaultdict(lambda : wn.NOUN)
   tag_map['J'] = wn.ADJ
   tag_map['V'] = wn.VERB
   tag_map['R'] = wn.ADV
   Final_words = []
   #stemming = PorterStemmer()
   word_Lemmatized = WordNetLemmatizer()
   # for index,entry in enumerate(text):
   for word,tag in pos_tag(text):
       if word not in stopwords.words('english') and word.isalpha():
           word_final = word_Lemmatized.lemmatize(word,tag_map[tag[0]])
           Final_words.append(word_final)
   return Final_words

def trainmodel():
    pos_tweets = twitter_samples.strings('positive_tweets.json')
    neg_tweets = twitter_samples.strings('negative_tweets.json')
    random.seed(1)  
    pos_tweets_set = []
    print("Training Phase")
    for tweet in pos_tweets:
       tweet = process_data(cleanText(tweet))
       pos_tweets_set.append((tweet, 'pos'))
    print("POS added in positive tweets")   
    neg_tweets_set = []
    for tweet in neg_tweets:
       tweet = process_data(cleanText(tweet))
       neg_tweets_set.append((tweet, 'neg'))
    print("NEG added in negative tweets") 
    shuffle(pos_tweets_set)
    shuffle(neg_tweets_set)
    #test_set = pos_tweets_set[:100] + neg_tweets_set[:100]
    train_set = pos_tweets_set[100:2000] + neg_tweets_set[100:2000]
    print("Training started by naive bayes classifier")
    classifier = NaiveBayesClassifier(train_set)
    print("Training finished")
    # accuracy = classifier.accuracy(test_set)
    # print(accuracy*100)
    #print("Server started with model accuracy:"+str(accuracy*100))
    return classifier