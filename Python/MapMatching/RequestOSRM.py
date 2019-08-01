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
            [geom_list_driving, trip_avg_conf, trip_avg_speed, trip_avg_diff_gps_snapped] = check_points(fin_trip_data,
                                                                                                 Mode.ModeEnum.DRIVING)
            if len(geom_list_driving) > 0:
                matched_cord_list_driving = Decode.decode_string(geom_list_driving)
                Visual.show_matched_map(matched_cord_list_driving, fin_trip_data, Mode.ModeEnum.DRIVING, trip)
                geom_list_driving.clear()
                matched_cord_list_driving.clear()
                print('Driving response generated for trip id: ' + str(trip))
                print('Response generated with avg confidence: ' + str(trip_avg_conf))
                print('Response generated with avg difference from actual GPS points: ' + str(trip_avg_diff_gps_snapped))
                print('Response generated with avg speed: ' + str(trip_avg_speed))
            else:
                print('Driving response not generated for trip id: ' + str(trip))

            [geom_list_biking, trip_avg_conf, trip_avg_speed, trip_avg_diff_gps_snapped] = check_points(fin_trip_data,
                                                                                                   Mode.ModeEnum.BIKING)
            if len(geom_list_biking) > 0:
                matched_cord_list_biking = Decode.decode_string(geom_list_biking)
                Visual.show_matched_map(matched_cord_list_biking, fin_trip_data, Mode.ModeEnum.BIKING, trip)
                geom_list_biking.clear()
                matched_cord_list_biking.clear()
                print('Biking response generated for trip id: ' + str(trip))
                print('Response generated with avg confidence: ' + str(trip_avg_conf))
                print('Response generated with avg difference from actual GPS points: ' + str(trip_avg_diff_gps_snapped))
                print('Response generated with avg speed: ' + str(trip_avg_speed))
            else:
                print('Biking response not generated for trip id: ' + str(trip))

            [geom_list_walking, trip_avg_conf, trip_avg_speed, trip_avg_diff_gps_snapped] = check_points(fin_trip_data,
                                                                                                   Mode.ModeEnum.WALKING)
            if len(geom_list_walking) > 0:
                matched_cord_list_walking = Decode.decode_string(geom_list_walking)
                Visual.show_matched_map(matched_cord_list_walking, fin_trip_data, Mode.ModeEnum.WALKING, trip)
                geom_list_walking.clear()
                matched_cord_list_walking.clear()
                print('Walking response generated for trip id: ' + str(trip))
                print('Response generated with avg confidence: ' + str(trip_avg_conf))
                print('Response generated with avg difference from actual GPS points: ' + str(trip_avg_diff_gps_snapped))
                print('Response generated with avg speed: ' + str(trip_avg_speed))
            else:
                print('Walking response not generated for trip id: ' + str(trip))


def check_points(fin_data, mode):

    geom_list = []
    trip_confidence = []
    trip_distance = []
    trip_duration = []
    gps_diff_dist = []
    tracepoints = []

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
            trip_confidence.append(response_osrm['matchings'][0]['confidence'])
            trip_distance.append(response_osrm['matchings'][0]['distance'])
            trip_duration.append(response_osrm['matchings'][0]['duration'])
            tracepoints.append(response_osrm['tracepoints'])

            for tracepoint in tracepoints[0]:
                if tracepoint is not None:
                    gps_diff_dist.append(tracepoint['distance'])

    trip_avg_conf = 0
    trip_avg_speed = 0
    trip_avg_diff_gps_snapped = 0

    if len(trip_confidence) > 0:
        trip_avg_conf = round(sum(trip_confidence) / len(trip_confidence), 2)
    if len(trip_distance) > 0 and len(trip_duration) > 0:
        trip_avg_speed = round(sum(trip_distance), 2) / round(sum(trip_duration), 2)
    if len(gps_diff_dist) > 0:
        trip_avg_diff_gps_snapped = round(sum(gps_diff_dist) / len(gps_diff_dist), 2)

    return [geom_list, trip_avg_conf, trip_avg_speed, trip_avg_diff_gps_snapped]
