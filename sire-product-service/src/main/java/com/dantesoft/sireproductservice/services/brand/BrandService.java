package com.dantesoft.sireproductservice.services.brand;

import java.util.UUID;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import com.dantesoft.sireproductservice.payload.request.brand.SaveBrandRequest;
import com.dantesoft.sireproductservice.payload.request.brand.UpdateBrandRequest;
import com.dantesoft.sireproductservice.payload.response.TaskResponse;
import com.dantesoft.sireproductservice.payload.response.brand.SimpleBrandToList;
import com.dantesoft.sireproductservice.payload.response.brand.SimpleBrandToShow;

public interface BrandService {

	Page<SimpleBrandToList> getBrandPage(Pageable pageable);

	TaskResponse saveBrand(SaveBrandRequest request);

	TaskResponse updateBrand(UUID uuid, UpdateBrandRequest request);

	SimpleBrandToShow showBrand(UUID uuid);

}
