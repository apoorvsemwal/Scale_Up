import requests
import json
import Modes as Mode
import DecodeGeometryString as Decode
import VisualizeData as Visual


def make_osrm_calls(fin_data, unq_trips):

    unq_trips = sorted(unq_trips)
    fin_data = sorted(fin_data)

    for trip in unq_trips:

        fin_trip_data = list()

        for trip_cord in fin_data:
            if trip == trip_cord[0]:
                fin_trip_data.append((trip_cord[2], trip_cord[3]))

        if len(fin_trip_data) > 0:
            geom_list_driving = check_points(fin_trip_data, Mode.ModeEnum.DRIVING)
            geom_list_biking = check_points(fin_trip_data, Mode.ModeEnum.BIKING)
            geom_list_walking = check_points(fin_trip_data, Mode.ModeEnum.WALKING)

            matched_cord_list_driving = Decode.decode_string(geom_list_driving)
            matched_cord_list_biking = Decode.decode_string(geom_list_biking)
            matched_cord_list_walking = Decode.decode_string(geom_list_walking)

            Visual.show_matched_map(matched_cord_list_driving, fin_trip_data, Mode.ModeEnum.DRIVING, trip)
            Visual.show_matched_map(matched_cord_list_biking, fin_trip_data, Mode.ModeEnum.BIKING, trip)
            Visual.show_matched_map(matched_cord_list_walking, fin_trip_data, Mode.ModeEnum.WALKING, trip)

        geom_list_driving.clear()
        geom_list_biking.clear()
        geom_list_walking.clear()

        matched_cord_list_driving.clear()
        matched_cord_list_biking.clear()
        matched_cord_list_walking.clear()


def check_points(fin_data, mode):

    geom_list = []

    # for i in range(len(fin_data) - 1):
    #     j = i + 1;
    #     request_url = 'http://108.61.89.209/osrm/match/v1/driving/'
    #     request_url = request_url + str(fin_data[i][1]) + ',' + str(fin_data[i][0]) + ';'
    #     request_url = request_url + str(fin_data[j][1]) + ',' + str(fin_data[j][0])
    #     request_url = request_url + '?steps=true&geometries=polyline&overview=full&annotations=true'
    #     response = requests.get(request_url)
    #     if response.status_code == 200:
    #         response_osrm = (json.loads(response.content.decode('utf-8')))
    #         geom_list.append(response_osrm['matchings'][0]['geometry'])
    #
    # return geom_list

# ########################## Unused Codes ###############################

    chunk_list = [fin_data[i * 8:(i + 1) * 8] for i in range((len(fin_data) + 7) // 8)]
    for sub_list in chunk_list:

        if mode.value == 1:
            request_url = 'http://108.61.89.209/osrm/match/v1/driving/'
        if mode.value == 2:
            request_url = 'http://108.61.89.209/osrm/match/v1/biking/'
        if mode.value == 3:
            request_url = 'http://108.61.89.209/osrm/match/v1/walking/'

        for lat_long in sub_list:
            request_url = request_url + str(lat_long[1]) + ',' + str(lat_long[0]) + ';'

        request_url = request_url[:-1]
        request_url = request_url + '?steps=true&geometries=polyline&overview=full&annotations=true' \
                                    '&geometries=polyline&gaps=ignore&tidy=true'
        response = requests.get(request_url)

        if response.status_code == 200:
            response_osrm = (json.loads(response.content.decode('utf-8')))
            geom_list.append(response_osrm['matchings'][0]['geometry'])

    return geom_list
