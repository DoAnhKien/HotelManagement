package com.khachsan.hotelmanament2.callback;

import com.khachsan.hotelmanament2.model.Service;

public interface MyServicesOnClicked {

    void handleMyServiceOnClicked(Service service, int position);

    void handleMyServiceOnLongClicked(Service service, int position);

}
