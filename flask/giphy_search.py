from flask import Flask
import json
import requests

app = Flask(__name__)

@app.route('/search/<search_term>')
def search(search_term):

    giphy_response = call_giphy_api(search_term)
    parsed_gifs = parse_giphy_response(giphy_response)

    return json.dumps({ 'data': parsed_gifs })

# makes the call to the giphy api and returns the response object
def call_giphy_api(search_term):
    giphy_search_endpoint = 'http://api.giphy.com/v1/gifs/search'
    giphy_request_parameters = {
        'api_key': 'dc6zaTOxFJmzC',
        'limit': 5,
        'q': search_term
    }
    return requests.get(url=giphy_search_endpoint, params=giphy_request_parameters)

# parses the giphy response object and converts the result to our desired format
def parse_giphy_response(giphy_response):
    results = giphy_response.json()['data']
    return [{'gif_id': result['id'], 'url': result['url']}
            for result in results] if len(results) > 4 else []

if __name__ == '__main__':
    app.run(port=8080, debug=True)
