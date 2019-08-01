import PrepareInputData as Pid
import RequestOSRM as Osrm


def main():

    connection = Pid.establish_db_connection()

    [fin_data, unq_trips] = Pid.prepare_input_data(connection)

    Osrm.make_osrm_calls(fin_data, unq_trips)

    Pid.close_db_connection(connection)


if __name__ == '__main__':
    main()
