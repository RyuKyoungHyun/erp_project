package com.erp.ezen25.service;

import com.erp.ezen25.dto.BrandDTO;
import com.erp.ezen25.dto.MemberDTO;
import com.erp.ezen25.dto.PageRequestDTO;
import com.erp.ezen25.dto.PageResultDTO;
import com.erp.ezen25.entity.Brand;
import com.erp.ezen25.entity.Member;
import com.erp.ezen25.entity.MemberRole;
import com.erp.ezen25.entity.QBrand;
import com.erp.ezen25.etc.KorToEng;
import com.erp.ezen25.repository.BrandRepository;
import com.erp.ezen25.repository.MemberRepository;
import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.dsl.BooleanExpression;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Collectors;

@Service
@Log4j2
@RequiredArgsConstructor
public class BrandServiceImpl implements BrandService {

    private final BrandRepository brandRepository;

    private final MemberRepository memberRepository;

    private final KorToEng converter;
    @Override
    public Long register(BrandDTO brandDTO) {
        log.info("-----------------------");
        log.info("DTO : " + brandDTO);
        log.info("-----------------------");
        Brand brand = dtoToEntity(brandDTO);

        brandRepository.save(brand);
        /*String convertedBrandName = converter.convertToRoman(brand.getBrandName());
        String password = convertedBrandName + LocalDateTime.now().getYear();
        Member member = Member.builder()
                .userId(brand.getBrandPhone())
                .password(password)
                *//*.authority("PARTNER")*//*
                .email(brand.getBrandEmail())
                .name(brand.getBrandName())
                .percent(0)
                .build();
        member.addMemberRole(MemberRole.PARTNER);

        memberRepository.save(member);*/

        return brand.getBrandId();
    }

    @Override
    public PageResultDTO<BrandDTO, Brand> getList(PageRequestDTO pageRequestDTO) {
        Pageable pageable = pageRequestDTO.getPageable(Sort.by("brandId").descending());

        BooleanBuilder booleanBuilder = getSearch(pageRequestDTO);

        Page<Brand> result = brandRepository.findAll(booleanBuilder, pageable);

        Function<Brand, BrandDTO> fn = (this::entityToDTO);

        return new PageResultDTO<>(result, fn);
    }

    @Override
    public BrandDTO read(Long brandId) {
        Optional<Brand> oBrand = brandRepository.findById(brandId);

        return oBrand.map(this::entityToDTO).orElse(null);
    }

    @Override
    @Transactional
    public void remove(Long brandId) {
        String brandName = "";
        Brand brand = new Brand();
        Member member = new Member();
        Long memberId = 0L;
        String name = "";

        Optional<Brand> oBrand = brandRepository.findById(brandId);

        if (oBrand.isPresent()) {
            brand = oBrand.get();
            brandName = brand.getBrandName();
        }
        Optional<Member> oMember = memberRepository.findByName(brandName);
        if (oMember.isPresent()) {
            member = oMember.get();
            name = member.getName();
            memberId = member.getMemberId();
        }
        if (brandName.equals(name)) {
            memberRepository.deleteById(memberId);
            brandRepository.deleteById(brandId);
            member.removeMemberRole(MemberRole.PARTNER);
        } else {
            brandRepository.deleteById(brandId);
        }


    }

    @Override
    public void modify(BrandDTO brandDTO) {
        Optional<Brand> oBrand = brandRepository.findById(brandDTO.getBrandId());

        if (oBrand.isPresent()) {
            Brand brand = oBrand.get();

            brand.changeBrandName(brandDTO.getBrandName());
            brand.changeBrandPhone(brandDTO.getBrandPhone());
            brand.changeBrandEmail(brandDTO.getBrandEmail());
            brand.changeBrandDescription(brandDTO.getBrandDescription());

            brandRepository.save(brand);
        }
    }

    private BooleanBuilder getSearch(PageRequestDTO pageRequestDTO) {
        String type = pageRequestDTO.getType();
        BooleanBuilder builder = new BooleanBuilder();
        QBrand qBrand = QBrand.brand;

        String keyword = pageRequestDTO.getKeyword();
        BooleanExpression expression = qBrand.brandId.gt(0L);
        builder.and(expression);

        if(type == null || type.trim().isEmpty()) {
            return builder;
        }

        BooleanBuilder sBuilder = new BooleanBuilder();

        if (type.contains("n")) {
               sBuilder.or(qBrand.brandName.contains(keyword));
        }
        if (type.contains("p")) {
            sBuilder.or(qBrand.brandPhone.contains(keyword));
        }
        if (type.contains("e")) {
            sBuilder.or(qBrand.brandEmail.contains(keyword));
        }
        if (type.contains("d")) {
            sBuilder.or(qBrand.brandDescription.contains(keyword));
        }

        builder.and(sBuilder);

        return builder;
    }

    @Override
    public String findBrandName(Long brandId) {
        Brand brand = brandRepository.findBrandByBrandId(brandId);
        if (brand != null) {
            return brand.getBrandName();
        } else {
            return "Unknown Brand";
        }
    }

    @Override
    public List<BrandDTO> getAllMembers() {
        List<Brand> brands = brandRepository.findAll();

        return brands.stream()
                .map(this::entityToDTO)
                .sorted(Comparator.comparing(BrandDTO::getBrandId).reversed()) // 내림차순으로 정렬하려는 필드를 선택 (getSomeField 대신 실제 필드를 사용)
                .limit(5)
                .collect(Collectors.toList());
    }

    @Override
    public int getNumberOfBrands() {
        List<Brand> brands = brandRepository.findAll();
        return brands.size();
    }
}
