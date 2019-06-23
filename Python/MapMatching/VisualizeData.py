import folium


def show_matched_map(matched_coord_list, fin_data, mode, trip):

    ave_lat = sum(p[0] for p in matched_coord_list) / len(matched_coord_list)
    ave_lon = sum(p[1] for p in matched_coord_list) / len(matched_coord_list)

    # Load map centred on average coordinates
    my_map = folium.Map(location=[ave_lat, ave_lon], zoom_start=12)

    end_flag = len(matched_coord_list)
    count = 0
    for each in matched_coord_list:
        if count == 0:
            folium.Marker(each, icon=folium.Icon(color='lightgreen'), popup="OSRM_START").add_to(my_map)
        elif count == (end_flag - 1):
            folium.Marker(each, icon=folium.Icon(color='cadetblue'), popup="OSRM_END").add_to(my_map)
        else:
            folium.Marker(each, icon=folium.Icon(color='green'), popup="OSRM").add_to(my_map)
        count += 1

    count = 0
    end_flag = len(fin_data)
    for each in fin_data:
        if count == 0:
            folium.Marker(each, icon=folium.Icon(color='lightred'), popup="GPS_START").add_to(my_map)
        elif count == (end_flag - 1):
            folium.Marker(each, icon=folium.Icon(color='orange'), popup="GPS_END").add_to(my_map)
        else:
            folium.Marker(each, icon=folium.Icon(color='red'), popup="GPS").add_to(my_map)
        count += 1


    # fadd lines
    folium.PolyLine(matched_coord_list, color="green", weight=2.5, opacity=1).add_to(my_map)
    folium.PolyLine(fin_data, color="red", weight=2.5, opacity=1).add_to(my_map)

    # Save map
    matched_map_name = "./OutPutMaps/MapMatched" + mode.name + "_" + str(trip) + ".html"
    my_map.save(matched_map_name)
