import psycopg2


def establish_db_connection():
    try:
        connection = psycopg2.connect(user="postgres",
                                      password="Canyon7*",
                                      host="127.0.0.1",
                                      port="5432",
                                      database="MapMatchingDB")
        return connection
    except (Exception, psycopg2.Error) as error:
        print('Error while connecting to PostgresSQL', error)
        return None


def close_db_connection(connection):
    if connection:
        connection.close()

# 3373857,3373984,3102385,1987034


def prepare_input_data(connection):
    lat_long_cur = connection.cursor()
    lat_long_cur.execute("SELECT trip.id, timestamp_txt, latitude, longitude "
                         "FROM public.interne_trips trip INNER JOIN public.interne_coordinates cord "
                         "ON trip.id = cord.trip_id WHERE cord.timestamp_txt >= trip.timestamp_start "
                         "AND cord.timestamp_txt <= trip.timestamp_end AND (trip.id = 2248034)")

    row_one = lat_long_cur.fetchone()
    fin_data = list()
    fin_data.append((row_one[0], row_one[1], row_one[2], row_one[3]))
    unq_trips = set()
    while row_one is not None:
        row_one = lat_long_cur.fetchone()
        if row_one is not None:
            unq_trips.add(row_one[0])
            fin_data.append((row_one[0], row_one[1], row_one[2], row_one[3]))
        else:
            break
    lat_long_cur.close()
    return [fin_data, unq_trips]
