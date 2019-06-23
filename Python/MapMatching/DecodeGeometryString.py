import polyline


def decode_string(geom_list):

    matched_cord_list = []
    for geom in geom_list:
        matched_cord_list.extend(polyline.decode(geom))

    return matched_cord_list
