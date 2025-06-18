export type PlantStage =
  | 'stage-1'
  | 'stage-2'
  | 'stage-3'
  | 'stage-4'
  | 'stage-5'
  | 'stage-6';

type PlantAnimationProps = {
  stage: PlantStage;
};

export default function PlantAnimation({ stage }: PlantAnimationProps) {
  const getImageSrc = (stage: PlantStage) => `/plants/${stage}.png`;
  const getAltText = (stage: PlantStage) => {
    switch (stage) {
      case 'stage-1':
        return '씨앗';
      case 'stage-2':
        return '새싹';
      case 'stage-3':
        return '줄기';
      case 'stage-4':
        return '꽃';
      case 'stage-5':
        return '꽃';
      case 'stage-6':
        return '꽃';
    }
  };

  const getSizeClass = (stage: PlantStage) => {
    switch (stage) {
      case 'stage-1':
        return 'w-24 h-24';
      case 'stage-2':
        return 'w-28 h-28';
      case 'stage-3':
        return 'w-32 h-32';
      case 'stage-4':
        return 'w-36 h-36';
      case 'stage-5':
        return 'w-36 h-36';
      case 'stage-6':
        return 'w-36 h-36';
    }
  };

  return (
    <div className="flex justify-center animate-fade-in">
      <img
        src={getImageSrc(stage)}
        alt={getAltText(stage)}
        className={`plant-img ${getSizeClass(stage)}`}
      />
    </div>
  );
}
